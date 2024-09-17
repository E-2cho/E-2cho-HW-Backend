package org.e2cho.e2cho_HW.service.externalApi;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.config.DataGoKrClient;
import org.e2cho.e2cho_HW.config.DataGoKrClientConfig;
import org.e2cho.e2cho_HW.dto.externalApi.DataGoKr;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataGoKrApiService {

    private final DataGoKrClient dataGoKrClient;
    private final DataGoKrClientConfig dataGoKrClientConfig;

    public DataGoKr.Result getCurrentPM(String date){
        return dataGoKrClient.getCurrentPM(dataGoKrClientConfig.getApiKey(), "json", date, "PM10");
    }
}
