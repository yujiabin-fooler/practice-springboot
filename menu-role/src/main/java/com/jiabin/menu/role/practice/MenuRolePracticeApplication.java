package com.jiabin.menu.role.practice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.jiabin.menu.role.practice.dao")
public class MenuRolePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuRolePracticeApplication.class,args);
    }
}
