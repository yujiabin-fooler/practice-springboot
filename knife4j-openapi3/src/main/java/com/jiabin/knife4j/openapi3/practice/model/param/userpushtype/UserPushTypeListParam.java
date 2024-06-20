package com.jiabin.knife4j.openapi3.practice.model.param.userpushtype;

import com.jiabin.knife4j.openapi3.practice.model.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户消息推送方式参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 17:17:19
 */
@Data
@Schema(description = "用户消息推送方式查询列表")
public class UserPushTypeListParam extends BasePageParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id ")
    private Long id;



}
