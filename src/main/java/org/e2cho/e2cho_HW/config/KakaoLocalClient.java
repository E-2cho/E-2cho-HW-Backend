package org.e2cho.e2cho_HW.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "KakaoLocalClient",
        url = "${kakaoLocal-service.urls.base-url}",
        configuration = KakaoLocalClientConfig.class)
public interface KakaoLocalClient {

    @GetMapping(headers = {"Content-Type=application/json"})
    String getRegionByCoordinates(@RequestParam("x") double longitude,
                                  @RequestParam("y") double latitude);

}
