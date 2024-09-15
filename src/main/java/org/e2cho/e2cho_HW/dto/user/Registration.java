package org.e2cho.e2cho_HW.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.e2cho.e2cho_HW.domain.user.User;

public class Registration {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{

        @NotBlank(message = "생년월일은 공백일 수 없습니다.")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "올바른 생년월일 형식은 yyyy-MM-dd 입니다.")
        private String birthdate; // 생년월일

        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Size(max = 20, message = "닉네임은 최대 20자리까지 입력해주세요.")
        private String nickname; // 닉네임

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long id;

        private String birthdate;

        private String nickname;

        public static Dto fromEntity(User user){
            return Dto.builder()
                    .id(user.getId())
                    .birthdate(user.getBirthdate())
                    .nickname(user.getNickname())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private Long id;

        private String birthdate;

        private String nickname;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .id(dto.getId())
                    .birthdate(dto.getBirthdate())
                    .nickname(dto.getNickname())
                    .build();

        }

    }
}
