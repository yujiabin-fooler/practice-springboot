package com.jiabin.rokctmq.practice.domain;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RocketMQMessage<T> implements Serializable {

    /**
     * 消息队列主题
     */
    @NotBlank(message = "MQ Topic 不能为空")
    private String topic;

    /**
     * 延迟级别
     */
    @Builder.Default
    private DelayLevel delayLevel = DelayLevel.OFF;

    /**
     * 消息体
     */
    private T message;

    /**
     * 消息体
     */
    private List<T> messages;

    /**
     * 使用有序消息发送时，指定发送到队列
     */
    private String hashKey;

    /**
     * 任务Id，用于日志打印相关信息
     */
    @Builder.Default
    private String taskId = IdUtil.fastSimpleUUID();

    public RocketMQMessage(String topic, T message) {
        this.topic = topic;
        this.message = message;
    }

    public RocketMQMessage(String topic, List<T> messages) {
        this.topic = topic;
        this.messages = messages;
    }

    public RocketMQMessage(String topic, DelayLevel delayLevel, T message) {
        this.topic = topic;
        this.delayLevel = delayLevel;
        this.message = message;
    }

    public RocketMQMessage(String topic, T message, String hashKey) {
        this.topic = topic;
        this.message = message;
        this.hashKey = hashKey;
    }

    public RocketMQMessage(String topic, List<T> messages, String hashKey) {
        this.topic = topic;
        this.messages = messages;
        this.hashKey = hashKey;
    }

    public Message<T> getMessage() {
        return ObjectUtils.isEmpty(this.message) ? null : MessageBuilder.withPayload(this.message).build();
    }

    public List<Message<T>> getMessages() {
        return CollectionUtils.isEmpty(this.messages) ? null :
                this.messages
                        .stream()
                        .map(content -> MessageBuilder.withPayload(content).build())
                        .collect(Collectors.toList());
    }
}
