package com.jiabin.redission.practice.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiabin.yu
 * @Description:
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() {
        return Redisson.create();
    }
}
