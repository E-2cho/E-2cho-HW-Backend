package org.e2cho.e2cho_HW.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.e2cho.e2cho_HW.domain.user.User;

public class NicknameRegistration {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Size(max = 20, message = "닉네임은 최대 20자리까지 입력해주세요.")
        private String nickname; // 닉네임

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long id;

        private String nickname;

        public static Dto fromEntity(User user){
            return Dto.builder()
                    .id(user.getId())
                    .nickname(user.getNickname())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private Long id;

        private String nickname;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .id(dto.getId())
                    .nickname(dto.getNickname())
                    .build();

        }

    }
}
