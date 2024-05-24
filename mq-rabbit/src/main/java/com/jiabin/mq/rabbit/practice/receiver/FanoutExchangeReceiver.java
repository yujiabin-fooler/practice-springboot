package com.jiabin.mq.rabbit.practice.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FanoutExchangeReceiver {

    /**
     * 监听simpleQueue队列的消息，进行消费
     */
    @RabbitListener(queues = "fanoutQueue.A")
    public void fanoutHandleA(String msg) {
        log.info("fanoutHandleA消费消息: " + msg);
    }

    /**
     * 监听simpleQueue队列的消息，进行消费
     */
    @RabbitListener(queues = "fanoutQueue.B")
    public void fanoutHandleB(String msg) {
        log.info("fanoutHandleB消费消息: " + msg);
    }

    /**
     * 监听simpleQueue队列的消息，进行消费
     */
    @RabbitListener(queues = "fanoutQueue.C")
    public void fanoutHandleC(String msg) {
        log.info("fanoutHandleC消费消息: " + msg);
    }
}
