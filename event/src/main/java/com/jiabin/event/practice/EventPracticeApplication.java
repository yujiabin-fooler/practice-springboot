package com.jiabin.event.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventPracticeApplication.class, args);
    }
}
