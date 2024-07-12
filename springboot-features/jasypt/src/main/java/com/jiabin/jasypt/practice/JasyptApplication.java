package com.jiabin.jasypt.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.jiabin.jasypt.practice")
@SpringBootApplication
public class JasyptApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(JasyptApplication.class, args);
    }
}
