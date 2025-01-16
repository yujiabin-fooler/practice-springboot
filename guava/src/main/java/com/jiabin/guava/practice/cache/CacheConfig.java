package com.jiabin.guava.practice.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jiabin.guava.practice.domain.User;

@Configuration
public class CacheConfig {

  @Bean
  Cache<Long, User> userCache() {
    Cache<Long, User> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build() ;
    return cache ;
  }
}
