package com.jiabin.guava.practice.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jiabin.guava.practice.domain.User;

public class Test1 {

  public static void main(String[] args) throws Exception {
    LoadingCache<Long, User> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build(
           new CacheLoader<Long, User>() {
             @Override
             public User load(Long key) {
               System.out.println("从DB加载数据") ;
               return new User(1L, "Pack", 33) ;
             }
           });
    System.out.println(cache.get(1L)) ;
    System.out.println(cache.get(1L)) ;
    System.out.println(cache.get(1L)) ;
  }
  
}
