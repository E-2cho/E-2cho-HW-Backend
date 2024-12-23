package org.e2cho.e2cho_HW.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_HW.dto.user.DeviceRegistration;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "userDevice")
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long id;

    @Column(nullable = false)
    private String deviceName;

    // User 와의 관계 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserDevice(String deviceName, User user) {
        this.deviceName = deviceName;
        this.user = user;
    }

    public static UserDevice of(User validateUser, DeviceRegistration.Request request){
        return UserDevice.builder()
                .deviceName(request.getDeviceName())
                .user(validateUser)
                .build();
    }
}
