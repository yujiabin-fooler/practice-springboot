package com.jiabin.mq.rabbit.practice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MoreToMoreQueueConfig {

    /**
     * 模拟多个生产者和多个消费者同时工作
     */
    @Bean
    public Queue moreToMoreQueue() {
        return new Queue("moreToMoreQueue");
    }
}
