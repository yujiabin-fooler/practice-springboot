package com.jiabin.security.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.jiabin.security.practice.mapper")
public class SecurityPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityPracticeApplication.class, args);
	}
}
