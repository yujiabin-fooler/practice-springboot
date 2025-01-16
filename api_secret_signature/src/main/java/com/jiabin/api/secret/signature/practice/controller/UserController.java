package com.jiabin.api.secret.signature.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.api.secret.signature.practice.annotation.DecryptRequest;
import com.jiabin.api.secret.signature.practice.annotation.EncryptResponse;
import com.jiabin.api.secret.signature.practice.annotation.Signature;
import com.jiabin.api.secret.signature.practice.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {
  
  private final Logger logger = LoggerFactory.getLogger(getClass()) ;

  @GetMapping("/{id}")
  @Signature
  public User queryUser(@PathVariable Long id) {
    return new User(id, "姓名 - " + id, id.intValue()) ;
  }
  
  @PostMapping("")
  @DecryptRequest
  @EncryptResponse
  public User save(@RequestBody User user) {
    logger.error("获取到请求数据: {}", user) ;
    return user ;
  }
}
