package com.example.ms_recomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRecommApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(MsRecommApplication.class, args);
	}

}
