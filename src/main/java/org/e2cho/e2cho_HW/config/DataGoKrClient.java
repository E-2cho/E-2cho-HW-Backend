package org.e2cho.e2cho_HW.config;

import org.e2cho.e2cho_HW.dto.weather.DataGoKr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "DataGoKrClient",
        url = "${dataGoKr-service.urls.base-url}",
        configuration = DataGoKrClientConfig.class)
public interface DataGoKrClient {

    @GetMapping(headers = {"Content-Type=application/json"})
    DataGoKr.Result getCurrentPM(
            @RequestParam("serviceKey") String apiKey,
            @RequestParam("returnType") String type,
            @RequestParam("searchDate") String date,
            @RequestParam("informCode") String code

    );

}
