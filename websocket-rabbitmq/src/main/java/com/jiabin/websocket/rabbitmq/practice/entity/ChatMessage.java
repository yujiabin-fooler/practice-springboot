package com.jiabin.websocket.rabbitmq.practice.entity;

import lombok.Data;

@Data
public class ChatMessage {

    private String content;
    private String sender;

}