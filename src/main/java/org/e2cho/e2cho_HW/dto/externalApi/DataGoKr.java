package org.e2cho.e2cho_HW.dto.externalApi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


public class DataGoKr {

    @Data
    public static class Result implements Serializable{

        private Response response;

        @Data
        public static class Response implements Serializable{
            private Body body;
            private Header header;
        }

        @Data
        public static class Header implements Serializable{
            private String resultMsg;
            private String resultCode;

        }
        @Data
        public static class Body  implements Serializable{
            private int totalCount;
            private List<Item> items;
            private int pageNo;
            private int numOfRows;
        }

        @Data
        public static class Item implements Serializable{
            private String informCode;
            private String informCause;
            private String informOverall;
            private String informData; // requestParam으로 넘긴 date와 비교
            private String informGrade;
            private String dataTime; // 최신 데이터 시간 확인
            private String imageUrl1;
            private String imageUrl2;
            private String imageUrl3;
            private String imageUrl4;
            private String imageUrl5;
            private String imageUrl6;

        }


    }
}
