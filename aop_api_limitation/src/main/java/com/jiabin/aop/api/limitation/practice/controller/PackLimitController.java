package com.jiabin.aop.api.limitation.practice.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.aop.api.limitation.practice.annotation.PackLimit;

@RestController
@RequestMapping("/limits")
public class PackLimitController {

  @PackLimit(
      count = 3, 
      key = "'PackLimitController.index'", 
      time = 3, 
      unit = TimeUnit.SECONDS, 
      message = "超过访问次数"
  )
  @GetMapping("/index")
  public String index() {
    return "index..." ;
  }
  
  
  @PackLimit(
      count = 3, 
      key = "request.getRemoteAddr()", 
      time = 3, 
      unit = TimeUnit.SECONDS, 
      message = "超过访问次数"
  )
  @GetMapping("/index2")
  public String index2() {
    System.out.println(1 / 0) ;
    return "index2..." ;
  }
  
  @PackLimit(
      count = 3, 
      key = "#xxxooo", 
      time = 3, 
      unit = TimeUnit.SECONDS, 
      message = "超过访问次数"
      )
  @GetMapping("/index3")
  public String index3() {
    return "index3..." ;
  }
}
