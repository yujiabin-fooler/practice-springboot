package com.jiabin.banner.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BannerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BannerApplication.class, args);
    }
}
