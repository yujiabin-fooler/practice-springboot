package com.jiabin.redis.message.practice.redissenior.collections;

import java.util.Random;

import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.data.redis.support.collections.RedisMap;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rediscollections")
public class RedisCollectionsController {

  private final RedisList<String> redisList ;
  private final RedisMap<String, String> userMap ;
  private final RedisZSet<String> userZSet ;
  public RedisCollectionsController(
      RedisList<String> redisList, 
      RedisMap<String, String> userMap,
      RedisZSet<String> userZSet) {
    this.redisList = redisList ;
    this.userMap = userMap ;
    this.userZSet = userZSet ;
  }
  
  @GetMapping("/list")
  public String list() {
    this.redisList.add("pack") ;
    this.redisList.add("jack") ;
    this.redisList.add("aack") ;
    this.redisList.add("xxxooo") ;
    return "success" ;
  }
  
  @GetMapping("/map")
  public String map() {
    this.userMap.put("name", "pack") ;
    this.userMap.put("age", "23") ;
    this.userMap.put("email", "xxxooo@qq.com") ;
    return "success" ;
  }
  
  @GetMapping("/zset")
  public String zset() {
    this.userZSet.add("pack", new Random().nextDouble(10000)) ;
    this.userZSet.add("jack", new Random().nextDouble(10000)) ;
    this.userZSet.add("xxxooo", new Random().nextDouble(10000)) ;
    return "success" ;
  }
}
