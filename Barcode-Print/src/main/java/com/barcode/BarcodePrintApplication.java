package com.barcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@SpringBootApplication
public class BarcodePrintApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarcodePrintApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
	return new LayoutDialect();
	}

}
