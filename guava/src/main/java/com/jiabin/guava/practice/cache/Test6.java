package com.jiabin.guava.practice.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jiabin.guava.practice.domain.User;

public class Test6 {

  public static void main(String[] args) throws Exception {
    LoadingCache<Long, User> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .refreshAfterWrite(10, TimeUnit.SECONDS)
        .build(
           new CacheLoader<Long, User>() {
             @Override
             public User load(Long key) {
               System.out.printf("从DB重新加载【%d】数据%n", key) ;
               return new User(1L, "Pack-New", 33) ;
             }
           });
    cache.put(1L, new User(1L, "Pack", 33));
    System.out.println(cache.get(1L)) ;
    TimeUnit.SECONDS.sleep(5) ;
    System.out.printf("等待5秒后，加载【%d】: %s%n", 1L, cache.get(1L)) ;
    TimeUnit.SECONDS.sleep(6) ;
    System.out.printf("等待11秒，加载【%d】: %s%n", 1L, cache.get(1L)) ;
  }
  
}
