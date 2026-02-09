package com.jew.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.jew")
@MapperScan("com.jew.mapper")
//@EnableJpaRepositories(basePackages = "com.jew.repository")
public class JewProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(JewProjApplication.class, args);
	}

}
