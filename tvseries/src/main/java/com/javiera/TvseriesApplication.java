package com.javiera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.javiera") 
public class TvseriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvseriesApplication.class, args);
	}

}