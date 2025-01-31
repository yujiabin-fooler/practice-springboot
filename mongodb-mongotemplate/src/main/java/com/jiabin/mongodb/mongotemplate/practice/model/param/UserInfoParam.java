package com.jiabin.mongodb.mongotemplate.practice.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 参数接收类
 *
 * @author junqiang.lu
 * @date 2021-01-06 20:03:33
 */
@Data
@ApiModel(value = "查询详情(单条)", description = "查询详情(单条)")
public class UserInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     **/
    @NotBlank(message = "id 不能为空")
    @Length(max = 64, message = "id 需要控制在 64 字符以内")
    @ApiModelProperty(value = "id不能为空", name = "id")
    private String id;


}
