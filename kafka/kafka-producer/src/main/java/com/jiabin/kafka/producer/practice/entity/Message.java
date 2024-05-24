package com.jiabin.kafka.producer.practice.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = -118L;
    /**
     * 发送人
     */
    private String sendUserName;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送内容
     */
    private String sendContent;
}
