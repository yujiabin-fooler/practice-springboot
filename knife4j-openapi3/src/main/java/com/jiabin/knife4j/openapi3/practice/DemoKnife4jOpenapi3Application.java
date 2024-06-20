package com.jiabin.knife4j.openapi3.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author dell
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.jiabin.knife4j.openapi3.practice.mapper"})
public class DemoKnife4jOpenapi3Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoKnife4jOpenapi3Application.class, args);
    }

}
