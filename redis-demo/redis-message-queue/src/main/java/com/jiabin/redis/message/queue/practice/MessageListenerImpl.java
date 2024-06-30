package com.jiabin.redis.message.queue.practice;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerImpl implements MessageListener {


    private void processOrder(byte[] orderData) {
        // 处理订单逻辑
        System.out.println("Processing order: " + orderData.toString());
        // 假设订单处理成功
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] orderData = message.getBody();
        processOrder(orderData);
    }
}