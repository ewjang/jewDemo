package com.jew.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jew")
public class JewProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(JewProjApplication.class, args);
	}

}
