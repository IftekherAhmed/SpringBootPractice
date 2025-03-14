package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@SpringBootApplication
public class BlogApplication {

    // Main method to run the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

    // Bean definition for LayoutDialect
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}
