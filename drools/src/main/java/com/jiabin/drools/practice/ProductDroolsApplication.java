package com.jiabin.drools.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ProductDroolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDroolsApplication.class, args);
	}

}