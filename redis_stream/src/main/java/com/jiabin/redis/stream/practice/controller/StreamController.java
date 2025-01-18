package com.jiabin.redis.stream.practice.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.redis.stream.practice.append.AppendComponent;
import com.jiabin.redis.stream.practice.domain.OperatorLog;

@RestController
@RequestMapping("/streams")
public class StreamController {

  private final AppendComponent appendComponent ;

  public StreamController(AppendComponent appendComponent) {
    this.appendComponent = appendComponent;
  }
  
  @GetMapping("/append")
  public String append() {
    return this.appendComponent.add(new OperatorLog("人员管理", "删除人员", "pack", new Date())).getValue() ;
  }
  
  
}
