package com.jiabin.mq.rabbit.practice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AckExchangeConfig {

    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue");
    }

    @Bean
    public DirectExchange ackExchange() {
        return new DirectExchange("ackExchange");
    }

    @Bean
    public Binding bindingAckExchange(Queue ackQueue, DirectExchange ackExchange) {
        return BindingBuilder.bind(ackQueue).to(ackExchange).with("ackRoutingKey");
    }
}
