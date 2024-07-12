package com.jiabin.easy.es.practice.config;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * EasyEs配置类
 * @author jiabin.yu 2022/9/16.
 */
@Configuration
@EsMapperScan("com.jiabin.easy.es.practice")
public class EasyEsConfig {
}
