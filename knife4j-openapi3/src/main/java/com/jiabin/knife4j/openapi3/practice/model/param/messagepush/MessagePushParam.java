package com.jiabin.knife4j.openapi3.practice.model.param.messagepush;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 消息推送请求参数
 * @Author jiabin.yu
 * @Date: 2023/8/16
 */
@Data
@Schema(description = "消息推送请求参数")
public class MessagePushParam implements Serializable {

    private static final long serialVersionUID = -9163471284052059262L;

    @Schema(description = "消息id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long messageId;

}
