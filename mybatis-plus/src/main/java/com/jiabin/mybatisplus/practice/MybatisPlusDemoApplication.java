package com.jiabin.mybatisplus.practice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiabin.mybatisplus.practice.mapper")
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }
}



