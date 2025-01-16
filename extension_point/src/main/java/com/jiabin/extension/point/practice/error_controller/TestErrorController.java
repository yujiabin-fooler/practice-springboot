package com.jiabin.extension.point.practice.error_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testerror")
public class TestErrorController {

  @GetMapping("")
  public String get() {
    System.out.println(1 / 0) ;
    return "get" ;
  }
  
  @PostMapping("")
  public String post() {
    System.out.println(1 / 0) ;
    return "post" ;
  }
  
}
