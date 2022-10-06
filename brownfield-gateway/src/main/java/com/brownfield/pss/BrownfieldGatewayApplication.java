package com.brownfield.pss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BrownfieldGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrownfieldGatewayApplication.class, args);
	}

}
