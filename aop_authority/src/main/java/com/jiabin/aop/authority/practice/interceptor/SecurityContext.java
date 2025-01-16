package com.jiabin.aop.authority.practice.interceptor;

import com.jiabin.aop.authority.practice.domain.User;

public class SecurityContext {

  private final static ThreadLocal<User> LOCAL = new ThreadLocal<>() ;
  
  public static void set(User user) {
    LOCAL.set(user) ; 
  }
  
  public static User get() {
    return LOCAL.get() ;
  }
}
