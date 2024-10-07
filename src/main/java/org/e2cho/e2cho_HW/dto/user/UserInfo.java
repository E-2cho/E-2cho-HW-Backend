package org.e2cho.e2cho_HW.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_HW.domain.user.User;

public class UserInfo {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto {

        private Long userId;

        private String nickname;

        public static Dto fromEntity(User foundUserByDeviceName){
            return Dto.builder()
                    .userId(foundUserByDeviceName.getId())
                    .nickname(foundUserByDeviceName.getNickname())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {

        private String message;

        private Long userId;

        private String nickname;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .message("유저 정보 조회에 성공하였습니다.")
                    .userId(dto.getUserId())
                    .nickname(dto.getNickname())
                    .build();
        }
    }
}
