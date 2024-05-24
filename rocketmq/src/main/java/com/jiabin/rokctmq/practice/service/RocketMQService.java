package com.jiabin.rokctmq.practice.service;

import com.jiabin.rokctmq.practice.domain.RocketMQMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RocketMQ 消息工具类
 *
 * @author MENGJIAO
 */
@Slf4j
@Component
public class RocketMQService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.sendMessageTimeout}")
    private int sendMessageTimeout;

    /**
     * 异步发送消息回调
     *
     * @param taskId 任务Id
     * @param topic  消息主题
     * @return the send callback
     */
    private static SendCallback asyncSendCallback(String taskId, String topic) {
        return new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("ROCKETMQ 异步消息发送成功 : [TaskId:{}] - [Topic:{}] - [SendStatus:{}]", taskId, topic, sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("ROCKETMQ 异步消息发送失败 : [TaskId:{}] - [Topic:{}] - [ErrorMessage:{}]", taskId, topic, throwable.getMessage());
            }
        };
    }

    /**
     * 发送同步消息，使用有序发送请设置HashKey
     *
     * @param message 消息参数
     */
    public <T> void syncSend(RocketMQMessage<T> message) {
        log.info("ROCKETMQ 同步消息发送 : [TaskId:{}] - [Topic:{}]", message.getTaskId(), message.getTopic());
        SendResult sendResult;
        if (StringUtils.isNotBlank(message.getHashKey())) {
            sendResult = rocketMQTemplate.syncSendOrderly(message.getTopic(), message.getMessage(), message.getHashKey());
        } else {
            sendResult = rocketMQTemplate.syncSend(message.getTopic(), message.getMessage(), sendMessageTimeout, message.getDelayLevel().getLevel());
        }
        log.info("ROCKETMQ 同步消息发送结果 : [TaskId:{}] - [Topic:{}] - [MessageId:{}] - [SendStatus:{}]",
                message.getTaskId(), message.getTopic(), sendResult.getMsgId(), sendResult.getSendStatus());
    }

    /**
     * 批量发送同步消息
     *
     * @param message 消息参数
     */
    public <T> void syncSendBatch(RocketMQMessage<T> message) {
        log.info("ROCKETMQ 同步消息-批量发送 : [TaskId:{}] - [Topic:{}] - [MessageCount:{}]",
                message.getTaskId(), message.getTopic(), message.getMessages().size());
        SendResult sendResult;
        if (StringUtils.isNotBlank(message.getHashKey())) {
            sendResult = rocketMQTemplate.syncSendOrderly(message.getTopic(), message.getMessages(), message.getHashKey());
        } else {
            sendResult = rocketMQTemplate.syncSend(message.getTopic(), message.getMessages());
        }
        log.info("ROCKETMQ 同步消息-批量发送结果 : [TaskId:{}] - [Topic:{}] - [MessageId:{}] - [SendStatus:{}]",
                message.getTaskId(), message.getTopic(), sendResult.getMsgId(), sendResult.getSendStatus());
    }

    /**
     * 异步发送消息，异步返回消息结果
     *
     * @param message 消息参数
     */
    public <T> void asyncSend(RocketMQMessage<T> message) {
        log.info("ROCKETMQ 异步消息发送 : [TaskId:{}] - [Topic:{}]", message.getTaskId(), message.getTopic());
        if (StringUtils.isNotBlank(message.getHashKey())) {
            rocketMQTemplate.asyncSendOrderly(message.getTopic(), message.getMessage(), message.getHashKey(),
                    asyncSendCallback(message.getTaskId(), message.getTopic()));
        } else {
            rocketMQTemplate.asyncSend(message.getTopic(), message.getMessage(),
                    asyncSendCallback(message.getTaskId(), message.getTopic()), sendMessageTimeout, message.getDelayLevel().getLevel());
        }
    }

    /**
     * 批量异步发送消息
     *
     * @param message 消息参数
     */
    public <T> void asyncSendBatch(RocketMQMessage<T> message) {
        log.info("ROCKETMQ 异步消息-批量发送 : [TaskId:{}] - [Topic:{}] - [MessageCount:{}]",
                message.getTaskId(), message.getTopic(), message.getMessages().size());
        if (StringUtils.isNotBlank(message.getHashKey())) {
            rocketMQTemplate.asyncSendOrderly(message.getTopic(), message.getMessages(), message.getHashKey(),
                    asyncSendCallback(message.getTaskId(), message.getTopic()));
        } else {
            rocketMQTemplate.asyncSend(message.getTopic(), message.getMessages(),
                    asyncSendCallback(message.getTaskId(), message.getTopic()));
        }
    }

    /**
     * 单向发送消息，不关心返回结果，容易消息丢失，适合日志收集、不精确统计等消息发送;
     *
     * @param message 消息参数
     */
    public <T> void sendOneWay(RocketMQMessage<T> message) {
        sendOneWay(message, false);
    }

    /**
     * 单向消息 - 批量发送
     *
     * @param message 消息体
     * @param batch   是否为批量操作
     */
    public <T> void sendOneWay(RocketMQMessage<T> message, boolean batch) {
        log.info((batch ? "ROCKETMQ 单向消息发送 : [TaskId:{}] - [Topic:{}]"
                        : "ROCKETMQ 单向消息-批量发送 : [TaskId:{}] - [Topic:{}] - [MessageCount{}]"),
                message.getTaskId(), message.getTopic(), message.getMessages().size());
        if (StringUtils.isNotBlank(message.getHashKey())) {
            if (batch) {
                message.getMessages().
                        forEach(msg -> rocketMQTemplate.sendOneWayOrderly(message.getTopic(), msg, message.getHashKey()));
            } else {
                rocketMQTemplate.sendOneWayOrderly(message.getTopic(), message.getMessage(), message.getHashKey());
            }
        } else {
            if (batch) {
                message.getMessages().forEach(msg -> rocketMQTemplate.sendOneWay(message.getTopic(), msg));
            } else {
                rocketMQTemplate.sendOneWay(message.getTopic(), message.getMessage());
            }
        }
    }
}
