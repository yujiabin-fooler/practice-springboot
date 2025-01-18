package com.jiabin.observation.practice.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.observation.practice.span.CustomObservation;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/obs")
@Timed(value = "ObservationController")
public class ObservationController {

  private final CustomObservation co ;
  public ObservationController(CustomObservation co) {
    this.co = co ;
  }
  
  @Timed(value = "ObservationController.list", extraTags = "list")
  @GetMapping("/list")
  public String list() throws Exception {
    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000)) ;
    return "list" ;
  }
  
  @Timed(value = "ObservationController.pay", extraTags = "pay")
  @GetMapping("/pay")
  public String pay() throws Exception {
    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000)) ;
    int ret = new Random().nextInt(3) ;
    if (ret == 0) {
      throw new RuntimeException("数字错误") ;
    }
    return "pay" ;
  }
  
  @GetMapping("/query")
  public String query() {
    this.co.query() ;
    return "success" ;
  }
}
