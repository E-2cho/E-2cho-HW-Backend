package org.e2cho.e2cho_HW.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_HW.dto.user.NicknameRegistration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname; // 사용자 닉네임


    @Builder
    public User(
            Long id,
            String nickname
    ) {
        this.id = id;
        this.nickname = nickname;
    }

    public static User registrationRequestToUser(
            NicknameRegistration.Request request
    ) {

        return User.builder()
                .nickname(request.getNickname())
                .build();
    }
}
