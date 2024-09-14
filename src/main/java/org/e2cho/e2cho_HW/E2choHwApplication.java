package org.e2cho.e2cho_HW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class E2choHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(E2choHwApplication.class, args);
	}

}
