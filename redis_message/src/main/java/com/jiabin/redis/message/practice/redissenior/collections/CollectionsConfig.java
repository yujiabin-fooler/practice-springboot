package com.jiabin.redis.message.practice.redissenior.collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.data.redis.support.collections.DefaultRedisZSet;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.data.redis.support.collections.RedisMap;
import org.springframework.data.redis.support.collections.RedisZSet;

@Configuration
public class CollectionsConfig {

  @Bean
  RedisList<String> userList(StringRedisTemplate stringRedisTemplate) {
    return new DefaultRedisList<>("pack:data:list", stringRedisTemplate) ;
  }
  
  @Bean
  RedisMap<String, String> userMap(StringRedisTemplate stringRedisTemplate) {
    return new DefaultRedisMap<>("pack:data:map", stringRedisTemplate) ;
  }
  
  @Bean
  RedisZSet<String> userZSet(StringRedisTemplate stringRedisTemplate) {
    return new DefaultRedisZSet<>("pack.data.zset", stringRedisTemplate) ;
  }
  
}
