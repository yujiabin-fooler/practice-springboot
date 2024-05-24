package com.jiabin.itextpdf.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ItextpdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItextpdfApplication.class, args);
	}
}
