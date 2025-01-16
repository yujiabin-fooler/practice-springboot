package com.jiabin.aop.dynamic.advice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jiabin.aop.dynamic.advice.domain.User;

@Service
public class UserService {

  public List<User> queryUsers() {
    return List.of(new User(1L, "Pack"), new User(2L, "Jook")) ;
  }
}
