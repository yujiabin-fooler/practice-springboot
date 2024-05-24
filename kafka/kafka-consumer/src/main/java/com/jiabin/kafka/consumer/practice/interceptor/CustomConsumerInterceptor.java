package com.jiabin.kafka.consumer.practice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

/**
 * 消费者拦截器
 */
@Slf4j
public class CustomConsumerInterceptor implements ConsumerInterceptor {
    /**
     * 拉取消息时，被调用
     *
     * @param consumerRecords
     * @return
     */
    @Override
    public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {

        log.error("消息{}已被拉取", consumerRecords.toString());
        return consumerRecords;
    }

    @Override
    public void close() {

    }

    /**
     * 提交请求响应成功时被调用
     *
     * @param map
     */
    @Override
    public void onCommit(Map map) {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
