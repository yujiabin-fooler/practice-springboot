package com.jiabin.multi.threading.tx.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.pack.mybatis.mapper"})
public class SpringBootMultiThreadingTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultiThreadingTxApplication.class, args);
	}
}
