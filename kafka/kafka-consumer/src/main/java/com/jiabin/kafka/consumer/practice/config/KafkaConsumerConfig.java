package com.jiabin.kafka.consumer.practice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiabin.yu
 * @version 1.0.0
 * @ClassName KafkaConfiguration.java
 * @Description TODO
 * @createTime 2023年02月19日 11:05:00
 */
@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;

	@Value("${spring.kafka.consumer.max-poll-records}")
	private Integer maxPollRecords;

	@Value("${spring.kafka.consumer.batch.concurrency}")
	private Integer batchConcurrency;

	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private Boolean autoCommit;

	@Value("${spring.kafka.consumer.auto-commit-interval}")
	private Integer autoCommitInterval;

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapServers;

	/**
	 *  消费者配置信息
	 */
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}

	/**
	 *  消费者批量工厂
	 */
	@Bean
	public KafkaListenerContainerFactory<?> batchFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
		//设置并发量，小于或等于Topic的分区数
		factory.setConcurrency(batchConcurrency);
		factory.getContainerProperties().setPollTimeout(1500);
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		//设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
		factory.setBatchListener(true);
		return factory;
	}

}
