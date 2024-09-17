package org.e2cho.e2cho_HW.dto.externalApi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class OpenWeatherMap {

    @Data
    public static class Response implements Serializable {
        private Coord coord;
        private List<Weather> weather;
        private String base;
        private Main main;
        private int visibility;
        private Wind wind;
        private Clouds clouds;
        private long dt;
        private Sys sys;
        private int timezone;
        private long id;
        private String name;
        private int cod;
    }

    @Data
    public static class Coord implements Serializable {
        private double lon;
        private double lat;
    }

    @Data
    public static class Weather implements Serializable {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main implements Serializable {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    @Data
    public static class Wind implements Serializable {
        private double speed;
        private int deg;
    }

    @Data
    public static class Clouds implements Serializable {
        private int all;
    }

    @Data
    public static class Sys implements Serializable {
        private int type;
        private long id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
