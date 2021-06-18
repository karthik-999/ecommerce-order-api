package com.order.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommerceOrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrderApiApplication.class, args);
	}

}
