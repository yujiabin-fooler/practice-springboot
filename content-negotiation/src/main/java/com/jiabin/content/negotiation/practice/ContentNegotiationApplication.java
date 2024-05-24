package com.jiabin.content.negotiation.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ContentNegotiationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentNegotiationApplication.class, args);
	}

}