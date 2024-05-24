package com.jiabin.rabbitmq.deadqueue.practice.dead;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RabbitTemplateConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 通过实现 ReturnsCallback 接口，启动消息失败返回，如果正确到达队列不执行。
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            System.out.println("消息主体 message : " + returnedMessage.getMessage());
            System.out.println("消息主体 message : " + returnedMessage.getReplyCode());
            System.out.println("描述：" + returnedMessage.getReplyText());
            System.out.println("消息使用的交换器 exchange : " + returnedMessage.getExchange());
            System.out.println("消息使用的路由键 routing : " + returnedMessage.getRoutingKey());
        });

        // 消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback((correlationData, arrival, cause) -> {
            assert correlationData != null;
            if (arrival) {
                log.info("消息已发送到交换机，MessageId：{}", correlationData.getId());
            } else {
                log.info("消息发送失败，MessageId：{}，失败原因：{}", correlationData.getId(), cause);
            }
        });
    }
}
