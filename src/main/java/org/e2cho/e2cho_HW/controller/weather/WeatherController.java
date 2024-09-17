package org.e2cho.e2cho_HW.controller.weather;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.dto.weather.ParticulateMatter;
import org.e2cho.e2cho_HW.dto.weather.Weather;
import org.e2cho.e2cho_HW.service.weather.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{id}/currentWeather")
    public ResponseEntity<Weather.Response> getCurrentWeather(@PathVariable("id") Long userId){

        Weather.Dto dto = weatherService.getCurrentWeather(userId);

        return new ResponseEntity<>(Weather.Response.createNewResponse(dto), HttpStatus.OK);

    }

    @GetMapping("/{id}/currentPM")
    public ResponseEntity<ParticulateMatter.Response> getCurrentPM(@PathVariable("id") Long userId){

        ParticulateMatter.Dto dto = weatherService.getCurrentPM(userId);

        return new ResponseEntity<>(ParticulateMatter.Response.createNewResponse(dto), HttpStatus.OK);
    }
}
