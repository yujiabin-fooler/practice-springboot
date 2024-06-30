package com.jiabin.redis.full.page.cache.practice;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {


//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
//         RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60)))
//                .transactionAware()
//                .build();
//
//    }

//    
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheManager cacheManager = new RedisCacheManager(connectionFactory);
//        // 设置缓存过期时间（例如5分钟）
//        cacheManager.setDefaultExpiration(300);
//        return cacheManager;
//    }


    @Bean
    public RedisConnectionFactory reactiveRedisConnectionFactory(RedisStandaloneConfiguration clientConfig) {

        return new LettuceConnectionFactory(clientConfig);
    }


    @Bean
    public RedisStandaloneConfiguration clientConfig(){
        return new RedisStandaloneConfiguration();

    }




}


