package org.e2cho.e2cho_HW.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ParticulateMatter {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto{

        private String regionName; // 지역명

        private String pmStatus; // 지역 미세먼지 등급

        private String updatedTime; // 업데이트 시간

        public static Dto of(String regionName, String pmStatus, String updatedTime){
            return Dto.builder()
                    .regionName(regionName)
                    .pmStatus(pmStatus)
                    .updatedTime(updatedTime)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String regionName;

        private String pmStatus;

        private String updatedTime;

        public static Response createNewResponse(Dto dto){
            return Response.builder()
                    .regionName(dto.getRegionName())
                    .pmStatus(dto.getPmStatus())
                    .updatedTime(dto.updatedTime)
                    .build();
        }
    }
}
