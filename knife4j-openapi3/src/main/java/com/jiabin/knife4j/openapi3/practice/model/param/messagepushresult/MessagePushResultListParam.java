package com.jiabin.knife4j.openapi3.practice.model.param.messagepushresult;

import com.jiabin.knife4j.openapi3.practice.model.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 消息推送结果参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "消息推送结果查询列表")
public class MessagePushResultListParam extends BasePageParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id ")
    private Long id;



}
