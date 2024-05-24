package com.jiabin.kafka.producer.practice.util;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author jiabin.yu
 * @version 1.0.0
 * @ClassName KafkaUtils.java
 * @Description TODO
 * @createTime 2022年05月22日 19:53:00
 */
@Component
@Slf4j
public class KafkaUtils {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void send(String topic, Object message) {
		String messageJson = "";
		if (null != message) {
			messageJson = JSON.toJSONString(message);
		}
		// 发送消息
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, messageJson);

		// 监听回调
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("发送消息失败......");
			}

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.error("成功发送信息：{}......", result);
			}
		});
	}
}
