package org.e2cho.e2cho_HW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients // openFeign 사용 가능
@EnableJpaAuditing
@SpringBootApplication
public class E2choHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(E2choHwApplication.class, args);
	}

}
