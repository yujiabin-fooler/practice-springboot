package com.jiabin.service.annotation.practice.impl;

import com.jiabin.service.annotation.practice.IAuthenticationService;
import com.jiabin.service.annotation.practice.domain.User;

public class UserAuthenticationService implements IAuthenticationService {

  @Override
  public User autenticate(User user) {
    System.err.printf("基于User认证, 认证用户: %s%n", user) ;
    return user ;
  }

}
