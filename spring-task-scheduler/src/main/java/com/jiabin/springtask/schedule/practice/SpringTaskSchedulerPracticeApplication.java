package com.jiabin.springtask.schedule.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringTaskSchedulerPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTaskSchedulerPracticeApplication.class, args);
	}
}
