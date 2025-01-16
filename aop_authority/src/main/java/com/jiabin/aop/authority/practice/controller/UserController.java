package com.jiabin.aop.authority.practice.controller;

import com.jiabin.aop.authority.practice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService ;
  public UserController(UserService userService) {
    this.userService = userService ;
  }
  
  @GetMapping("/login")
  public String login(String username, String password) {
    return this.userService.login(username, password) ;
  }
}
