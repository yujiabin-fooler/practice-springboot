package com.jiabin.refreshscope.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.refreshscope.practice.scope.ContextRefresher;

@RestController
@RequestMapping("/refresh")
public class RefreshController {

  private final ContextRefresher contextRefresher ;
  public RefreshController(ContextRefresher contextRefresher) {
    this.contextRefresher = contextRefresher ;
  }
  
  @GetMapping("")
  public Object refresh() {
    this.contextRefresher.refresh();
    return "success" ;
  }
  
}
