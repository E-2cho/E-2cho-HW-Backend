package org.e2cho.e2cho_HW.config;

import org.e2cho.e2cho_HW.dto.weather.OpenWeatherMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "OpenWeatherMapClient",
        url = "${openWeatherMap-service.urls.base-url}",
        configuration = OpenWeatherMapClientConfig.class)

public interface OpenWeatherMapClient {

        @GetMapping(value = "/weather", headers = "{Content-Type=application/json}")
        OpenWeatherMap.Response getCurrentWeather(
                @RequestParam("lat") double latitude,
                @RequestParam("lon") double longitude,
                @RequestParam("appid") String apiKey
        );

}
