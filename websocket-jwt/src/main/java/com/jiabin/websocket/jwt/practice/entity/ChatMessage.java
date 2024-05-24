package com.jiabin.websocket.jwt.practice.entity;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String recipient;
    private String message;
}