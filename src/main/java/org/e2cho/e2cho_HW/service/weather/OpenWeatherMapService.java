package org.e2cho.e2cho_HW.service.weather;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.config.OpenWeatherMapClient;
import org.e2cho.e2cho_HW.config.OpenWeatherMapClientConfig;
import org.e2cho.e2cho_HW.dto.weather.OpenWeatherMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapService {

    private final OpenWeatherMapClient openWeatherMapClient;
    private final OpenWeatherMapClientConfig openWeatherMapClientConfig;

    public OpenWeatherMap.Response getCurrentWeather(double latitude, double longitude){
        return openWeatherMapClient.getCurrentWeather(latitude, longitude, openWeatherMapClientConfig.getApiKey());
    }
}
