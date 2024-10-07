package org.e2cho.e2cho_HW.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;

@Configuration
@ConfigurationProperties
@Indexed
@Data
public class DataGoKrClientConfig {

    @Value("${dataGoKr-service.http-client.read-timeout}")
    private int readTimeout;

    @Value("${dataGoKr-service.http-client.connect-timeout}")
    private int connectTimeout;

    @Value("${dataGoKr-service.api-key}")
    private String apiKey;  // 여기에서 apiKey 값을 주입 받음


    @Bean(name = "dataGoKrFeignOptions")
    public Request.Options options() {
        return new Request.Options(getConnectTimeout(), getReadTimeout());
    }

    @Bean(name = "dataGoKrFeignLogger")
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean(name = "dataGoKrFeignRetryer")
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
