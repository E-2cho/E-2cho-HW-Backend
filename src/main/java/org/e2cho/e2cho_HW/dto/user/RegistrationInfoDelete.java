package org.e2cho.e2cho_HW.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_HW.domain.user.User;

public class RegistrationInfoDelete {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long deletedId; // 삭제된 유저 id
        private String deletedUserNickname; // 삭제된 유저 닉네임

        public static Dto of(Long deletedId, String deletedUserNickname){
            return Dto.builder()
                    .deletedId(deletedId)
                    .deletedUserNickname(deletedUserNickname)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private Long deletedId;
        private String deletedUserNickname;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .deletedId(dto.deletedId)
                    .deletedUserNickname(dto.deletedUserNickname)
                    .build();
        }
    }
}
