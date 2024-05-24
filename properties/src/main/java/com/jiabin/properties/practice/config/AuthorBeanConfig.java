package com.jiabin.properties.practice.config;

import com.jiabin.properties.practice.entity.AuthorBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * AuthorBeanConfig 使用 @Bean 注解的方式获取配置信息
 *
 **/
@Configuration
@PropertySource(value = "classpath:author.properties")
public class AuthorBeanConfig {

    @Bean
    @ConfigurationProperties(prefix = "author")
    public AuthorBean getAuthorBean(){
       return new AuthorBean();
    }
}
