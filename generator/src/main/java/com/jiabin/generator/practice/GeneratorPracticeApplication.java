package com.jiabin.generator.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiabin.generator.practice.mapper")
public class GeneratorPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorPracticeApplication.class, args);
	}

}
