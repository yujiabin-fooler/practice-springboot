package com.jiabin.dubbo.consumer.client.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.springboot.dubbo")
public class DubboConsumerClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerClientApplication.class);
    }
}
