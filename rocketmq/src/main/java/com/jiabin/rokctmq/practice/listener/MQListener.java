package com.jiabin.rokctmq.practice.listener;

import com.jiabin.rokctmq.practice.constants.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * MQ消息监听
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = MQConfig.EVENT_TOPIC,
        consumerGroup = MQConfig.EVENT_CONSUMER_GROUP)
public class MQListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("MQListener 接收消息 ： {}", message);
    }
}
