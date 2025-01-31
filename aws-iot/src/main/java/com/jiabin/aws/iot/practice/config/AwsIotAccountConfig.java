package com.jiabin.aws.iot.practice.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 亚马逊 iot 服务账号配置类
 * @Author jiabin.yu
 * @Date: 2022/7/25
 */
@Getter
@ToString
@Configuration
public class AwsIotAccountConfig {

    /**
     * 终端节点
     */
    @Value(value = "${aws.iot.clientEndpoint}")
    private String clientEndpoint;

    /**
     * 接入公钥
     */
    @Value(value = "${aws.iot.accessKeyId}")
    private String accessKeyId;

    /**
     * 接入私钥
     */
    @Value(value = "${aws.iot.secretAccessKey}")
    private String secretAccessKey;


}
