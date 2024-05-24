package com.jiabin.mybatis.xml.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jiabin.mybatis.xml.practice.mapper")
public class MybatisXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisXmlApplication.class, args);
	}
}
