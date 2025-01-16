package com.jiabin.dynamic.mgr.pracitce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.dynamic.mgr.pracitce.service.UserService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {

  @Resource
  private UserService userService ;
  
  @GetMapping("/save")
  public Object save() {
    this.userService.save() ;
    return "save method" ;
  }
  
  @GetMapping("/remove")
  public Object remove() {
    this.userService.remove() ;
    return "remove method" ;
  }
}
