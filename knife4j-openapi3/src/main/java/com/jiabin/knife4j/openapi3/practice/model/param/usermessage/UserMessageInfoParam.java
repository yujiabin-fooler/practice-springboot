package com.jiabin.knife4j.openapi3.practice.model.param.usermessage;

import com.jiabin.knife4j.openapi3.practice.model.BaseInfoParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户消息参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "用户消息查询详情(单条)")
public class UserMessageInfoParam extends BaseInfoParam {

    private static final long serialVersionUID = 1L;


}
