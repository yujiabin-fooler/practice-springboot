package com.jiabin.mq.rabbit.practice.sender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 笑小枫 <https://www.xiaoxiaofeng.com/>
 * @date 2023/12/19
 */
@Component
@AllArgsConstructor
@Slf4j
public class DelaySender {

    private final AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend("delayQueue", msg);
        log.info("DelaySender 发送消息成功：" + msg);
    }
}
