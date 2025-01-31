package com.jiabin.ffmpeg.media.practice.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 文件上传配置信息
 * @Author jiabin.yu
 * @Date: 2024/5/10
 */
@Getter
@ToString
@Configuration
public class UploadConfig {

    /**
     * 文件上传保存路径
     */
    @Value("${upload.path}")
    private String uploadPath;


}
