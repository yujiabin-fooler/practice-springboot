package com.jiabin.rocketmq.spring.practice.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 消费者
 * 原始消费者api调用方式， 消费者订阅broker topic ，broker推送消息通过消费者监听获取消息体进行消费
 *
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        // 通过push模式消费消息，指定消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myPracticeConsumer");

        // 指定NameServer的地址
        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 订阅这个topic下的所有的消息
        consumer.subscribe("myPracticeTopic", "*");

        // 注册一个消费的监听器，当有消息的时候，会回调这个监听器来消费消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.printf("消费消息:%s", new String(msg.getBody()) + "\n");
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        // 启动消费者
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
