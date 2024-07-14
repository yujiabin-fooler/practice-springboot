package com.jiabin.shardingjdbc.practice.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 设备信息查询单条
 * @Author jiabin.yu
 * @Date: 2022/4/1
 */
@Data
public class DeviceInfoQueryOneParam implements Serializable {

    private static final long serialVersionUID = 3530487735864796135L;

    /**
     * id
     */
    private Long id;

}
