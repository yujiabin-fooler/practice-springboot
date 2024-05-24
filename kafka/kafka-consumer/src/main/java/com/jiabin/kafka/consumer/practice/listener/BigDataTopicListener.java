package com.jiabin.kafka.consumer.practice.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author jiabin.yu
 * @version 1.0.0
 * @ClassName BigDataTopicListener.java
 * @Description TODO
 * @createTime 2023年02月19日 11:09:00
 */
@Component
@Slf4j
public class BigDataTopicListener {


	/**
	 * 监听kafka数据（批量消费）
	 *
	 * @param records
	 * @param ack
	 */
	@KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "batchFactory")
	public void batchConsumer(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
		long start = System.currentTimeMillis();
		//手动提交
		ack.acknowledge();
		log.error("收到bigData推送的数据，拉取数据量：{}，消费时间：{}ms", records.size(), (System.currentTimeMillis() - start));
		if (!CollectionUtils.isEmpty(records)) {
			for (ConsumerRecord<?, ?> consumerRecord : records) {
				Optional message = Optional.ofNullable(consumerRecord);
				if (message.isPresent()) {
					Object msg = message.get();
					log.error("消费者接收到消息：{} ", msg);
				}
			}
		}


	}

}
