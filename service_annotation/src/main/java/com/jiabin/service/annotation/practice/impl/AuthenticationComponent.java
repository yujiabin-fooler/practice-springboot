package com.jiabin.service.annotation.practice.impl;

import com.jiabin.service.annotation.practice.AbstractAuthenticationService;
import com.jiabin.service.annotation.practice.IAuthenticationService;
import com.jiabin.service.annotation.practice.domain.User;

//@Service
public class AuthenticationComponent extends AbstractAuthenticationService implements IAuthenticationService {

  @Override
  public User autenticate(User user) {
    System.err.println("自定义认证") ;
    return user ;
  }
  @Override
  public boolean support() {
    System.out.println("支持的认证方式") ;
    return true ;
  }
}
