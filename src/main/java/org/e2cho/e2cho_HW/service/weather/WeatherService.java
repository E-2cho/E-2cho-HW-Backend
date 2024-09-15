package org.e2cho.e2cho_HW.service.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_HW.domain.user.UserLocation;
import org.e2cho.e2cho_HW.dto.weather.DataGoKr;
import org.e2cho.e2cho_HW.dto.weather.OpenWeatherMap;
import org.e2cho.e2cho_HW.dto.weather.ParticulateMatter;
import org.e2cho.e2cho_HW.dto.weather.Weather;
import org.e2cho.e2cho_HW.repository.user.UserLocationRepository;
import org.e2cho.e2cho_HW.service.util.LocationUtilService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final UserLocationRepository userLocationRepository;

    private final OpenWeatherMapService openWeatherMapService;
    private final KakaoLocalService kakaoLocalService;
    private final DataGoKrService dataGoKrService;
    private final LocationUtilService locationUtilService;

    public Weather.Dto getCurrentWeather(Long userId){

        // 1. 유저의 현재 위치 가져오기
        UserLocation foundUserLocation = userLocationRepository.findByUserId(userId);

        // 2. 날씨 조회
        OpenWeatherMap.Response currentWeather = openWeatherMapService.getCurrentWeather(foundUserLocation.getLatitude(), foundUserLocation.getLongitude());

        return Weather.Dto.fromOpenWeatherMap(currentWeather);
    }

    public ParticulateMatter.Dto getCurrentPM(Long userId){

        // 0. 현재 날짜
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

        log.info("오늘 날짜 : {}", today);

        // 1. 유저의 현재 위치 가져오기
        UserLocation foundUserLocation = userLocationRepository.findByUserId(userId);

        // 2. 경위도를 대한민국 행정구역으로 변환
        String kakaoResponse = kakaoLocalService.getRegionByCoordinates(foundUserLocation.getLatitude(), foundUserLocation.getLongitude());

        // 2-1. 응답에서 행정구역(시/도) 및 구역 정보 추출
        String cityName = locationUtilService.getCityName(kakaoResponse);
        String districtName = locationUtilService.getDistrictName(kakaoResponse);

        // 2-2. 추출된 정보를 Data.Go.Kr 에서 조회한 정보와 상호작용할 수 있도록 준비
        String convertedCityName = locationUtilService.getConvertedCityName(cityName, districtName);

        // 3. 미세먼지 정보 조회
        DataGoKr.Result currentPM = null;

        if(LocalTime.now().isBefore(LocalTime.of(5,0))){
             currentPM = dataGoKrService.getCurrentPM(yesterday);

        } else {
             currentPM = dataGoKrService.getCurrentPM(today);
        }

        // 3-1. 조회 결과 정보 업데이트 시간 확인
        String updatedTime = currentPM.getResponse().getBody().getItems().get(0).getDataTime();

        // 3-2. 조회 결과와 변환된 도시명과 일치하는 도시의 미세먼지 상태 구하기.
        String informGrade = currentPM.getResponse().getBody().getItems().get(0).getInformGrade();
        String pmStatus = getInformGradeForCity(informGrade, convertedCityName);



        return ParticulateMatter.Dto.of(convertedCityName, pmStatus, updatedTime);

    }

    public String getInformGradeForCity(String informGrade, String cityName) {
        // 도시와 상태 값을 분리하여 Map에 저장
        Map<String, String> cityGradeMap = new HashMap<>();
        String[] cityGrades = informGrade.split(",");

        for (String cityGrade : cityGrades) {
            String[] parts = cityGrade.split(" : ");
            if (parts.length == 2) {
                cityGradeMap.put(parts[0].trim(), parts[1].trim());
            }
        }

        // 변환된 도시 이름에 대한 상태 값을 반환
        return cityGradeMap.getOrDefault(cityName, "정보 없음");
    }

}
