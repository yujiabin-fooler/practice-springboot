package com.jiabin.observation.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringBootObservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootObservationApplication.class, args);
	}

	
	@Scheduled(cron = "*/10 * * * * *")
  public void task() {
    System.err.printf("%s task execution...%n", Thread.currentThread().getName()) ;
  }
}
