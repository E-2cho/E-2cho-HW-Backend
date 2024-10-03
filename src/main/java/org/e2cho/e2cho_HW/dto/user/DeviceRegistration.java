package org.e2cho.e2cho_HW.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.e2cho.e2cho_HW.domain.user.UserDevice;

public class DeviceRegistration {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        @NotBlank(message = "기기명은 공백일 수 없습니다.")
        private String deviceName;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private Long userId;
        private String nickname;
        private String deviceName;

        public static Dto fromEntity(UserDevice newUserDevice){
            return Dto.builder()
                    .userId(newUserDevice.getUser().getId())
                    .nickname(newUserDevice.getUser().getNickname())
                    .deviceName(newUserDevice.getDeviceName())
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Long userId;
        private String nickname;
        private String deviceName;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .userId(dto.getUserId())
                    .nickname(dto.getNickname())
                    .deviceName(dto.getDeviceName())
                    .build();
        }
    }
}
