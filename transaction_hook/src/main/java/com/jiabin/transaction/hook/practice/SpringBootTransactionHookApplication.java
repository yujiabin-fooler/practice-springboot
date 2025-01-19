package com.jiabin.transaction.hook.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootTransactionHookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionHookApplication.class, args);
	}

}
