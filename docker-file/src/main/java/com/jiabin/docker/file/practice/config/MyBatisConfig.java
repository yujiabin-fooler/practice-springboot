package com.jiabin.docker.file.practice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @author jiabin.yu 2019/4/8.
 */
@Configuration
@MapperScan("com.jiabin.docker.file.practice.mbg.mapper")
public class MyBatisConfig {
}
