package com.jiabin.mvc.practice.controller;

import com.jiabin.mvc.practice.annotation.PackBody;
import com.jiabin.mvc.practice.annotation.PackController;
import com.jiabin.mvc.practice.annotation.PackRequest;
import com.jiabin.mvc.practice.domain.User;
import com.jiabin.mvc.practice.service.UserService;

@PackController
public class UserController {

  private final UserService userService ;
  public UserController(UserService userService) {
    this.userService = userService ;
  }
  
  @PackRequest("/query")
  public User query() {
    System.err.println(this.userService.query()) ;
    return new User(1L, "张三", 22) ;
  }
  
  @PackRequest("/search")
  public User search(Long id, String name, Integer age) {
    return new User(id, name, age) ;
  }
  
  @PackRequest("/body")
  public User body(@PackBody User user) {
    return user ;
  }
}
