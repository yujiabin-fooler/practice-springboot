package com.jiabin.cache.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // 开启缓存功能
public class CachePracticeApplication {

	public static void main(String[] args) {

		SpringApplication.run(CachePracticeApplication.class, args);
	}
}
