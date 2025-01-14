package com.jiabin.aws.iot.practice.device.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: iot 设备推送消息请求参数
 * @Author jiabin.yu
 * @Date: 2022/7/22
 */
@Data
public class IotDevicePublishParam implements Serializable {

    private static final long serialVersionUID = -7105642749638142299L;

    /**
     * 客户端 id
     */
    private String clientId;

    /**
     * 消息
     */
    private String message;


}
