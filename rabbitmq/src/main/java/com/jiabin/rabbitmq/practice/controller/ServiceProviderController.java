package com.jiabin.rabbitmq.practice.controller;

import com.jiabin.rabbitmq.practice.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 公众号：Java旅途
 * @Description 生产者
 * @Date 2020-10-04 14:44
 */
@RestController
public class ServiceProviderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public void sendMessage(){

        String message = "你好，我是Java旅途";
        rabbitTemplate.convertAndSend(RabbitConfig.exchangeName,null,message);
    }
}
