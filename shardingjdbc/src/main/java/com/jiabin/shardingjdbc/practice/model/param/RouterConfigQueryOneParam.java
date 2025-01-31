package com.jiabin.shardingjdbc.practice.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 路由器配置查询单条
 * @Author jiabin.yu
 * @Date: 2022/4/1
 */
@Data
public class RouterConfigQueryOneParam implements Serializable {

    private static final long serialVersionUID = 9152230337723728662L;

    /**
     * id
     */
    private Long id;
}
