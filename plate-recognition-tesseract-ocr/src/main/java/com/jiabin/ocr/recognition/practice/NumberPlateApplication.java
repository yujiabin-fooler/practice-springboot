package com.jiabin.ocr.recognition.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class NumberPlateApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(NumberPlateApplication.class, args);
	}

}