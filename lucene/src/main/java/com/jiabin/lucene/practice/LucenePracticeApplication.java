package com.jiabin.lucene.practice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.jiabin.lucene.practice.mapper"})
public class LucenePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LucenePracticeApplication.class, args);
    }

}