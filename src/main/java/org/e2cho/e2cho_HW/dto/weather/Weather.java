package org.e2cho.e2cho_HW.dto.weather;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.e2cho.e2cho_HW.dto.externalApi.OpenWeatherMap;

import java.util.List;
import java.util.stream.Collectors;

public class Weather {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Dto {

        @NotNull
        private Coord coord;

        @NotNull
        private List<WeatherDescription> weather;

        @NotNull
        private String base;

        @NotNull
        private Main main;

        @NotNull
        private int visibility;

        @NotNull
        private Wind wind;

        @NotNull
        private Clouds clouds;

        @NotNull
        private long dt;

        @NotNull
        private Sys sys;

        @NotNull
        private int timezone;

        @NotNull
        private long id;

        @NotNull
        private String name;

        @NotNull
        private int cod;

        // fromOpenWeatherMap 메서드
        public static Dto fromOpenWeatherMap(OpenWeatherMap.Response response) {
            return Dto.builder()
                    .coord(Coord.fromOpenWeatherMap(response.getCoord()))  // Coord 변환
                    .weather(response.getWeather().stream()
                            .map(WeatherDescription::fromOpenWeatherMap)
                            .collect(Collectors.toList()))  // Weather 변환
                    .base(response.getBase())
                    .main(Main.fromOpenWeatherMap(response.getMain()))  // Main 변환
                    .visibility(response.getVisibility())
                    .wind(Wind.fromOpenWeatherMap(response.getWind()))  // Wind 변환
                    .clouds(Clouds.fromOpenWeatherMap(response.getClouds()))  // Clouds 변환
                    .dt(response.getDt())
                    .sys(Sys.fromOpenWeatherMap(response.getSys()))  // Sys 변환
                    .timezone(response.getTimezone())
                    .id(response.getId())
                    .name(response.getName())
                    .cod(response.getCod())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Coord coord;
        private List<WeatherDescription> weather;
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

        // createNewResponse 메서드
        public static Response createNewResponse(Dto dto) {
            return Response.builder()
                    .coord(dto.getCoord())
                    .weather(dto.getWeather())
                    .base(dto.getBase())
                    .main(dto.getMain())
                    .visibility(dto.getVisibility())
                    .wind(dto.getWind())
                    .clouds(dto.getClouds())
                    .dt(dto.getDt())
                    .sys(dto.getSys())
                    .timezone(dto.getTimezone())
                    .id(dto.getId())
                    .name(dto.getName())
                    .cod(dto.getCod())
                    .build();
        }
    }

    // 필요한 내부 클래스 정의 및 변환 메서드 추가
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Coord {
        private double lon;
        private double lat;

        public static Coord fromOpenWeatherMap(OpenWeatherMap.Coord coord) {
            return Coord.builder()
                    .lon(coord.getLon())
                    .lat(coord.getLat())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class WeatherDescription {
        private int id;
        private String main;
        private String description;
        private String icon;

        public static WeatherDescription fromOpenWeatherMap(OpenWeatherMap.Weather weather) {
            return WeatherDescription.builder()
                    .id(weather.getId())
                    .main(weather.getMain())
                    .description(weather.getDescription())
                    .icon(weather.getIcon())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;

        public static Main fromOpenWeatherMap(OpenWeatherMap.Main main) {
            return Main.builder()
                    .temp(main.getTemp())
                    .feels_like(main.getFeels_like())
                    .temp_min(main.getTemp_min())
                    .temp_max(main.getTemp_max())
                    .pressure(main.getPressure())
                    .humidity(main.getHumidity())
                    .sea_level(main.getSea_level())
                    .grnd_level(main.getGrnd_level())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Wind {
        private double speed;
        private int deg;

        public static Wind fromOpenWeatherMap(OpenWeatherMap.Wind wind) {
            return Wind.builder()
                    .speed(wind.getSpeed())
                    .deg(wind.getDeg())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Clouds {
        private int all;

        public static Clouds fromOpenWeatherMap(OpenWeatherMap.Clouds clouds) {
            return Clouds.builder()
                    .all(clouds.getAll())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Sys {
        private int type;
        private long id;
        private String country;
        private long sunrise;
        private long sunset;

        public static Sys fromOpenWeatherMap(OpenWeatherMap.Sys sys) {
            return Sys.builder()
                    .type(sys.getType())
                    .id(sys.getId())
                    .country(sys.getCountry())
                    .sunrise(sys.getSunrise())
                    .sunset(sys.getSunset())
                    .build();
        }
    }
}
