package org.e2cho.e2cho_HW.config;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
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
public class KakaoLocalClientConfig {

    @Value("${kakaoLocal-service.http-client.read-timeout}")
    private int readTimeout;

    @Value("${kakaoLocal-service.http-client.connect-timeout}")
    private int connectTimeout;

    @Value("${kakaoLocal-service.api-key}")
    private String apiKey;  // 여기에서 apiKey 값을 주입 받음


    @Bean(name = "kakaoLocalFeignOptions")
    public Request.Options options() {
        return new Request.Options(getConnectTimeout(), getReadTimeout());
    }

    @Bean(name = "kakaoLocalFeignLogger")
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean(name = "kakaoLocalFeignRetryer")
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return request -> request.header("Authorization", "KakaoAK " + apiKey);
    }
}
