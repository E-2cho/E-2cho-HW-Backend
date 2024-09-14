package org.e2cho.e2cho_HW.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_HW.dto.user.Location;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "userLocation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // User 와의 관계 정의
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserLocation(double latitude, double longitude, LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public static UserLocation fromRequestDto(
            User user,
            Location.Request request
    ) {
        return UserLocation.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .user(user)
                .build();
    }

    public void update(Location.Request request){
        this.latitude = request.getLatitude();
        this.longitude = request.getLongitude();
        this.updatedAt = LocalDateTime.now();
    }

}

