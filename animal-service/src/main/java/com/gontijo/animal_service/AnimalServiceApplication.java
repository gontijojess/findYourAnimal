package com.gontijo.animal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnimalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalServiceApplication.class, args);
	}

}
