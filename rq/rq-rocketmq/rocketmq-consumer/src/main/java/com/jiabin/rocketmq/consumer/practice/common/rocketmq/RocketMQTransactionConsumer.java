package com.jiabin.rocketmq.consumer.practice.common.rocketmq;

import com.jiabin.rocketmq.consumer.practice.common.constant.RocketMQConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description: RocketMQ 事务消息消费者
 * @Author jiabin.yu
 * @Date: 2021/12/6
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = RocketMQConst.TOPIC_TRANSACTION, consumerGroup = RocketMQConst.GROUP_CONSUMER_TRANSACTION)
public class RocketMQTransactionConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("rocketMQ consumer, topic:{}, message:{}", RocketMQConst.TOPIC_TRANSACTION, s);
    }
}
