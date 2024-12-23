package org.e2cho.e2cho_HW.config;


import feign.Logger;
import feign.Request;
import feign.Retryer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;

@Configuration
@ConfigurationProperties
@Indexed
@Data
@Slf4j
public class OpenWeatherMapClientConfig {

    @Value("${openWeatherMap-service.http-client.read-timeout}")
    private int readTimeout;

    @Value("${openWeatherMap-service.http-client.connect-timeout}")
    private int connectTimeout;

    @Value("${openWeatherMap-service.api-key}")
    private String apiKey;  // 여기에서 apiKey 값을 주입 받음


    @Bean
    public Request.Options options() {
        return new Request.Options(getConnectTimeout(), getReadTimeout());
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }


}
