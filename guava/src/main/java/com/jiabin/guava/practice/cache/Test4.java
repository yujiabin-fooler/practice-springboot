package com.jiabin.guava.practice.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jiabin.guava.practice.domain.User;

public class Test4 {

  public static void main(String[] args) throws Exception {
    Cache<Long, User> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build() ;
    cache.put(1L, new User(1L, "Pack", 33)) ;
    System.out.println(cache.asMap()) ;
  }
  
}
