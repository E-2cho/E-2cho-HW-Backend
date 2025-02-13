package org.e2cho.e2cho_HW.service.externalApi;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.config.KakaoLocalClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLocalApiService {

    private final KakaoLocalClient kakaoLocalClient;

    public String getRegionByCoordinates(double latitude, double longitude){
        return kakaoLocalClient.getRegionByCoordinates(longitude, latitude);
    }
}
