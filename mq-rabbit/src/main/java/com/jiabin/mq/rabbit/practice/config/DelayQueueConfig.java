package com.jiabin.mq.rabbit.practice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DelayQueueConfig {
    @Bean
    public Queue delayDeadQueue() {
        return QueueBuilder
                .durable("delayDeadQueue")
                .deadLetterExchange("delay-exchange")
                .build();
    }

    @Bean
    public Queue delayQueue() {
        // 声明该队列的死信消息发送到的 交换机 (队列添加了这个参数之后会自动与该交换机绑定,并设置路由键,不需要开发者手动设置)
        // ttl = 5000，设置TTL，单位ms，5s 内没被消费，则进入死信队列
        return QueueBuilder
                .durable("delayQueue")
                .ttl(5000)
                .deadLetterExchange("delay-exchange")
                .build();
    }

    @Bean
    public TopicExchange delayExchange() {
        return new TopicExchange("delay-exchange");
    }

    @Bean
    public Binding delayBinding(Queue delayDeadQueue, TopicExchange delayExchange) {
        return BindingBuilder.bind(delayDeadQueue).to(delayExchange).with("#");
    }
}
