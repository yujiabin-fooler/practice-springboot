package com.jiabin.mq.rabbit.practice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SimpleQueueConfig {

    /**
     * 使用默认的交换机，进行消息发布消费
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simpleQueue");
    }
}