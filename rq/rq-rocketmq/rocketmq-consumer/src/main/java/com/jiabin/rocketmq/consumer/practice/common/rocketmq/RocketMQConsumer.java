package com.jiabin.rocketmq.consumer.practice.common.rocketmq;

import com.jiabin.rocketmq.consumer.practice.common.constant.RocketMQConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description: RocketMQ 默认主题消费者
 * @Author jiabin.yu
 * @Date: 2021/12/2
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = RocketMQConst.TOPIC_DEMO, consumerGroup = RocketMQConst.GROUP_CONSUMER_DEMO)
public class RocketMQConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        log.info("rocketMQ consumer, topic:{}, message:{}", RocketMQConst.TOPIC_DEMO, s);
    }
}
