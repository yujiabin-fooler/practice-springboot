package com.jiabin.kafka.producer.practice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 自定义生产者拦截器
 */
@Slf4j
public class CustomProducerInterceptor implements ProducerInterceptor {

    /**
     * 在发送前做一些处理
     *
     * @param producerRecord
     * @return
     */
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        log.info("正在发送消息: {}", producerRecord.value().toString());
        return producerRecord;
    }

    /**
     * 在消息应答前，或者消息发送失败时被调用
     *
     * @param recordMetadata
     * @param e
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    /**
     * 关闭interceptor，主要用于执行一些资源清理工作
     */
    @Override
    public void close() {

    }

    /**
     * 获取配置信息和初始化数据时调用
     *
     * @param map
     */
    @Override
    public void configure(Map<String, ?> map) {

    }
}
