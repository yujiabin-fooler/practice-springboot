package com.jiabin.druid.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.jiabin.druid.practice.mapper")
public class DruidPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidPracticeApplication.class, args);
	}
}
