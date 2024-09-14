package org.e2cho.e2cho_HW.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.domain.user.UserLocation;

import java.time.LocalDateTime;

public class Location {


    @Getter
    @Setter
    public static class Request{

        @NotNull
        private Double latitude;

        @NotNull
        private Double longitude;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String nickname; // 사용자 닉네임

        private double latitude;

        private double longitude;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public static Dto of(User user, UserLocation userLocation){

            return Dto.builder()
                    .nickname(user.getNickname())
                    .latitude(userLocation.getLatitude())
                    .longitude(userLocation.getLongitude())
                    .createdAt(userLocation.getCreatedAt())
                    .updatedAt(userLocation.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String nickname;

        private double latitude;

        private double longitude;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public static Response createNewResponse(Dto dto){

            return Response.builder()
                    .nickname(dto.getNickname())
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .createdAt(dto.getCreatedAt())
                    .updatedAt(dto.getUpdatedAt())
                    .build();
        }
    }
}
