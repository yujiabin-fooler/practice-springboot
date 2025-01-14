package com.jiabin.mongodb.mongotemplate.practice.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 按照更新时间分组查询博客数据
 * @Author jiabin.yu
 * @Date: 2021/11/18
 */
@Data
@ApiModel(value = "按照更新时间分组查询博客数据", description = "按照更新时间分组查询博客数据")
public class BlogQueryGroupByUpdateTimeParam implements Serializable {

    private static final long serialVersionUID = 2760417598516960498L;

    /**
     * 最小更新时间(时间戳,精确到毫秒)
     */
    @NotNull(message = "最小更新时间不能为空")
    @ApiModelProperty(value = "最小更新时间(时间戳,精确到毫秒)", name = "minUpdateTime", required = true, example = "0")
    private Long minUpdateTime;
    /**
     * 最大更新时间(时间戳,精确到毫秒)
     */
    @NotNull(message = "最大更新时间不能为空")
    @ApiModelProperty(value = "最大更新时间(时间戳,精确到毫秒)", name = "maxUpdateTime", required = true, example = "0")
    private Long maxUpdateTime;

}
