package com.jiabin.face.detection.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FaceDetectionApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FaceDetectionApplication.class, args);
	}

}