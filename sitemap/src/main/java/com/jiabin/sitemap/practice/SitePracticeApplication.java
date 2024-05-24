package com.jiabin.sitemap.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SitePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitePracticeApplication.class, args);
	}
}
