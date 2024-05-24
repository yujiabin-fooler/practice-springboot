package com.jiabin.kafka.consumer.practice.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

/**
 * 消息接收监听器
 */
//@Component
@Slf4j
public class MessageListener {

    @KafkaListener(id = "messageGroup", topics = "${spring.kafka.topic}")
    public void listenerMessage(ConsumerRecord<?, ?> record) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.error("消费者接收到消息：{} ", msg);
        }
    }
}
