package com.forItrum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:/main_properties/application.properties")
public class ForItrumApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForItrumApplication.class, args);
	}
}
