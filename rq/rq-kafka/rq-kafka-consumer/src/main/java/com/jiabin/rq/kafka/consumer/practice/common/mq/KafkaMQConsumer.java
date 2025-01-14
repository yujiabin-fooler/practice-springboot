package com.jiabin.rq.kafka.consumer.practice.common.mq;

import cn.hutool.json.JSONUtil;
import com.jiabin.rq.kafka.consumer.practice.common.constant.KafkaMessageConst;
import com.jiabin.rq.kafka.consumer.practice.entity.KafkaMessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description: Kafka 消息消费者
 * @Author jiabin.yu
 * @Date: 2022/2/25
 */
@Slf4j
@Component
public class KafkaMQConsumer {


    /**
     * 消息接受者
     * @param record
     */
    @KafkaListener(topics = {KafkaMessageConst.KAFKA_TOPIC_DEMO})
    public void receive(ConsumerRecord<?, ?> record) {
        log.info("record: {}", record);
        Optional.ofNullable(record.value())
                .ifPresent(message -> {
                    log.info("message: {}", JSONUtil.toBean(String.valueOf(message), KafkaMessageEntity.class));
                });
    }

}
