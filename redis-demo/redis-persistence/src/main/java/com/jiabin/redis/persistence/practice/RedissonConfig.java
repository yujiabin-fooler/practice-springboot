package com.jiabin.redis.persistence.practice;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redissonClient() {
//        RedissonClientConfig config = new RedissonClientConfig();
//        config.useSingleServer().setAddress("redis://" + spring.redis.host + ":" + spring.redis.port);
//        return Redisson.create(config);
//    }
//
//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;
}