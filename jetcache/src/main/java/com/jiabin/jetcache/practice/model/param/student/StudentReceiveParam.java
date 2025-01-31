package com.jiabin.jetcache.practice.model.param.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 接收学生信息(pb)
 * @Author jiabin.yu
 * @Date: 2021/8/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "接收学生信息(pb)", description = "查询学生信息")
public class StudentReceiveParam implements Serializable {

    private static final long serialVersionUID = -8856061586998092638L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
    /**
     * 出生日期(时间戳,精确到秒)
     */
    @ApiModelProperty(value = "出生日期(时间戳,精确到秒)", required = true, example = "1")
    private Integer birthDate;


}
