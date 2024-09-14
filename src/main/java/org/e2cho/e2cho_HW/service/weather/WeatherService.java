package org.e2cho.e2cho_HW.service.weather;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.domain.user.UserLocation;
import org.e2cho.e2cho_HW.dto.weather.OpenWeatherMap;
import org.e2cho.e2cho_HW.dto.weather.Weather;
import org.e2cho.e2cho_HW.repository.user.UserLocationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final UserLocationRepository userLocationRepository;

    private final OpenWeatherMapService openWeatherMapService;

    public Weather.Dto getCurrentWeather(Long userId){

        // 1. 유저의 현재 위치 가져오기
        UserLocation foundUserLocation = userLocationRepository.findByUserId(userId);

        // 2. 날씨 조회
        OpenWeatherMap.Response currentWeather = openWeatherMapService.getCurrentWeather(foundUserLocation.getLatitude(), foundUserLocation.getLongitude());

        return Weather.Dto.fromOpenWeatherMap(currentWeather);
    }
}
