package com.jiabin.springboot.model.practice.vo.ehcache3;

import com.jiabin.springboot.model.practice.BaseBean;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description: CacheRemove 注解测试 参数接收 bean
 * @Author jiabin.yu
 * @Date: 2019/3/16
 */
@Data
public class CacheRemoveBean extends BaseBean {

    private static final long serialVersionUID = -2815271308925839053L;

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    @Min(value = 1, message = "用户 id 至少为 1")
    private Long id;

}
