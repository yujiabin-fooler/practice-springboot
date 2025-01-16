package com.jiabin.guava.practice.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jiabin.guava.practice.domain.User;

public class Test5 {

  public static void main(String[] args) throws Exception {
    Cache<Long, User> cache = CacheBuilder.newBuilder()
        .expireAfterAccess(10, TimeUnit.MINUTES)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build() ;
    cache.put(1L, new User(1L, "Pack", 33)) ;
    System.out.println(cache.asMap()) ;
  }
  
}
