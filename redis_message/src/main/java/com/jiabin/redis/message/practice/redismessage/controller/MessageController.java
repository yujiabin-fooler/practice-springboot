package com.jiabin.redis.message.practice.redismessage.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final StringRedisTemplate stringRedisTemplate ;
  public MessageController(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate ;
  }
  
  @GetMapping("/send")
  public String send(String message) {
    this.stringRedisTemplate.convertAndSend("chat", message) ;
    return "success" ;
  }
}
