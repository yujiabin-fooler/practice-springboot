package com.jiabin.shardingjdbc.practice.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 设备信息新增
 * @Author jiabin.yu
 * @Date: 2022/4/1
 */
@Data
public class DeviceInfoAddParam implements Serializable {

    private static final long serialVersionUID = -5525158268443913054L;

    /**
     * 设备类型,1-路由器,2-音响,3-摄像头
     */
    private Integer type;
    /**
     * 设备品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备名称
     */
    private String name;


}
