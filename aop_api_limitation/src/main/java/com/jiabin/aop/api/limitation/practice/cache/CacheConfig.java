package com.jiabin.aop.api.limitation.practice.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  @Bean
  static PackCacheBeanPostProcessor packCacheBeanPostProcessor() {
    return new PackCacheBeanPostProcessor() ;
  }
}
