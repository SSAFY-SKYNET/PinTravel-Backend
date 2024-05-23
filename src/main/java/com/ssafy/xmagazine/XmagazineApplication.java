package com.ssafy.xmagazine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class XmagazineApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmagazineApplication.class, args);
	}

}
