package com.jiabin.oss.springtask.practice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @author jiabin.yu 2019/4/8.
 */
@Configuration
@MapperScan({"com.jiabin.oss.springtask.practice.mbg.mapper","com.jiabin.oss.springtask.practice.dao"})
public class MyBatisConfig {
}
