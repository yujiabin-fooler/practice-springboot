package com.jiabin.scheduled.task.practice.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledTaskPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskPracticeApplication.class,args);
    }

}
