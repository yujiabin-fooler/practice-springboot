package com.jiabin.mq.rabbit.practice.sender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 笑小枫 <https://www.xiaoxiaofeng.com/>
 * @date 2023/12/18
 */
@Component
@AllArgsConstructor
@Slf4j
public class FanoutExchangeSender {
    private final AmqpTemplate rabbitTemplate;

    /**
     * 直接没有配置交换机（exchange），使用默认的交换机
     */
    public void send(String msg) {
        rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
        log.info("FanoutExchangeSender 发送消息成功：" + msg);
    }
}
