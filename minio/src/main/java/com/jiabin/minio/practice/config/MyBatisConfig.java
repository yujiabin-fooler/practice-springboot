package com.jiabin.minio.practice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.jiabin.minio.practice.mbg.mapper")
public class MyBatisConfig {
}
