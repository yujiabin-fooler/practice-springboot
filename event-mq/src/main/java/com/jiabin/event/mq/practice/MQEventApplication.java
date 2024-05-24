package com.jiabin.event.mq.practice;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 启动类
 */
@Import(RocketMQAutoConfiguration.class)
@SpringBootApplication
public class MQEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQEventApplication.class, args);
    }
}
