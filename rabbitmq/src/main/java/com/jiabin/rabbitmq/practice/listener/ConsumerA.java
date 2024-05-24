package com.jiabin.rabbitmq.practice.listener;

import com.jiabin.rabbitmq.practice.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 公众号：Java旅途
 * @Description 消费者A
 * @Date 2020-10-04 14:36
 */

@RabbitListener(queues = RabbitConfig.queueNameA)
@Component
@Slf4j
public class ConsumerA {

    @RabbitHandler
    public void receive(String message){
        log.info("消费者A接收到的消息："+message);
    }
}
