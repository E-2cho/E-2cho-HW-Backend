package org.e2cho.e2cho_HW.service.util;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationUtilService {

    public String getCityName(String kakaoResponse){

        JSONObject kakaoJsonObject = new JSONObject(kakaoResponse);

        String cityName;

        cityName = kakaoJsonObject.getJSONArray("documents")
                .getJSONObject(0)
                .getString("region_1depth_name");

        return cityName;
    }

    public String getDistrictName(String kakaoResponse){

        JSONObject kakaoJsonObject = new JSONObject(kakaoResponse);
        String districtName;

        districtName = kakaoJsonObject.getJSONArray("documents")
                .getJSONObject(0)
                .getString("region_2depth_name");

        return districtName;
    }


    public String getConvertedCityName(String cityName, String districtName) {

        String convertedCityName = null;

        if (cityName.equals("경기도")) {
            if (districtName.equals("수원시") || districtName.equals("용인시") || districtName.equals("화성시") ||
                    districtName.equals("성남시") || districtName.equals("부천시") || districtName.equals("안산시") ||
                    districtName.equals("평택시") || districtName.equals("안양시") || districtName.equals("시흥시") ||
                    districtName.equals("김포시") || districtName.equals("광주시") || districtName.equals("하남시") ||
                    districtName.equals("광명시") || districtName.equals("군포시") || districtName.equals("오산시") ||
                    districtName.equals("이천시") || districtName.equals("안성시") || districtName.equals("의왕시") ||
                    districtName.equals("양평군") || districtName.equals("여주시") || districtName.equals("과천시")) {
                convertedCityName = "경기남부";
            } else if (districtName.equals("고양시") || districtName.equals("남양주시") || districtName.equals("파주시") ||
                    districtName.equals("의정부시") || districtName.equals("양주시") || districtName.equals("구리시") ||
                    districtName.equals("포천시") || districtName.equals("동두천시") || districtName.equals("가평군") ||
                    districtName.equals("연천군")) {
                convertedCityName = "경기북부";
            }
        } else if (cityName.equals("강원특별자치도")) {

            if (districtName.equals("강릉시") || districtName.equals("삼척시") || districtName.equals("동해시") ||
                    districtName.equals("태백시") || districtName.equals("속초시") || districtName.equals("양양군") ||
                    districtName.equals("고성군") || districtName.equals("통천군")) {
                convertedCityName = "영동";

            } else if (districtName.equals("철원군") || districtName.equals("화천군") || districtName.equals("양구군") ||
                    districtName.equals("인제군") || districtName.equals("춘천시") || districtName.equals("홍천군") ||
                    districtName.equals("횡성군") || districtName.equals("원주시") || districtName.equals("평창군") ||
                    districtName.equals("영월군") || districtName.equals("정선군")) {
                convertedCityName = "영서";
            }
        } else {

            convertedCityName = convertRegionName(cityName);
        }
        return convertedCityName;
    }

    public String convertRegionName(String regionName) {
        // 지역명 매핑을 위한 Map 생성
        Map<String, String> regionMap = new HashMap<>();
        regionMap.put("서울특별시", "서울");
        regionMap.put("제주특별자치도", "제주");
        regionMap.put("전라남도", "전남");
        regionMap.put("전라북도", "전북");
        regionMap.put("광주광역시", "광주");
        regionMap.put("울산광역시", "울산");
        regionMap.put("대구광역시", "대구");
        regionMap.put("부산광역시", "부산");
        regionMap.put("충청남도", "충남");
        regionMap.put("충청북도", "충북");
        regionMap.put("세종특별자치시", "세종");
        regionMap.put("경상북도", "경북");
        regionMap.put("경상남도", "경남");
        regionMap.put("대전광역시", "대전");
        regionMap.put("인천광역시", "인천");

        // 매핑된 값이 있으면 반환, 없으면 원래 값 반환
        return regionMap.getOrDefault(regionName, regionName);
    }
}
