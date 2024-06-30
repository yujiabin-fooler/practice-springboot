package com.jiabin.redis.data.out.of.date.practice;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@Configuration
@EnableRedisHttpSession
public class SessionConfig {
    // 配置类不需要额外代码，@EnableRedisHttpSession将自动配置所需的Bean
}
