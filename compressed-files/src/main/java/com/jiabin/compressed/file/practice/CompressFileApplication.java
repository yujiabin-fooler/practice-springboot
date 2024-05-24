package com.jiabin.compressed.file.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CompressFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompressFileApplication.class, args);
	}

}