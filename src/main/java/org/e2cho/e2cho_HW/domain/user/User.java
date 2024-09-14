package org.e2cho.e2cho_HW.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_HW.dto.user.Registration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname; // 사용자 닉네임

    @Column(nullable = false)
    private String birthdate; // 사용자 생일

    @Builder
    public User(
            Long id,
            String nickname,
            String birthdate
    ) {
        this.id = id;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }

    public static User registrationRequestToUser(
            Registration.Request request
    ) {

        return User.builder()
                .birthdate(request.getBirthdate())
                .nickname(request.getNickname())
                .build();
    }
}
