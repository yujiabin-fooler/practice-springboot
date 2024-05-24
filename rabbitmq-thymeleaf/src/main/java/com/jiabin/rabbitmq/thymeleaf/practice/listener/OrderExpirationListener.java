package com.jiabin.rabbitmq.thymeleaf.practice.listener;

import com.jiabin.rabbitmq.thymeleaf.practice.entity.Order;
import com.jiabin.rabbitmq.thymeleaf.practice.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderExpirationListener {
    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "order-expiration-queue")
    public void handleExpiredOrder(Long orderId) {
        // 根据orderId更新订单状态为过期
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setExpired(true);
            orderRepository.save(order);
        }
    }
}