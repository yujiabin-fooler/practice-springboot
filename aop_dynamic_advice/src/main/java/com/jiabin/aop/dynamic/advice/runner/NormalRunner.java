package com.jiabin.aop.dynamic.advice.runner;

import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jiabin.aop.dynamic.advice.service.UserService;

@Component
public class NormalRunner implements CommandLineRunner {

  private final UserService userService ;
  private final AbstractAutowireCapableBeanFactory beanFactory ;
  public NormalRunner(UserService userService, AbstractAutowireCapableBeanFactory beanFactory) {
    this.userService = userService ;
    this.beanFactory = beanFactory ;
  }
  
  @Override
  public void run(String... args) throws Exception {
    this.userService.queryUsers() ;
    System.out.println("============================") ;
    System.out.println(this.userService.getClass() + "@" + this.userService.hashCode()) ;
    
  }
}
