package com.jiabin.activiti.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jiabin.activiti.practice.mapper")
@SpringBootApplication
public class ActivitiPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiPracticeApplication.class, args);
    }

}
