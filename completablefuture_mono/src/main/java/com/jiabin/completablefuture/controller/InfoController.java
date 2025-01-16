package com.jiabin.completablefuture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class InfoController {

  @GetMapping("/users/{userId}")
  public String getScore(@PathVariable String userId) {
    try {
      TimeUnit.SECONDS.sleep(1) ;
    } catch (InterruptedException e) {}
    return String.format("用户【%s】500积分", userId) ;
  }

  @GetMapping("/stocks/{productId}")
  public String getStock(@PathVariable String productId) {
    try {
      TimeUnit.SECONDS.sleep(1) ;
    } catch (InterruptedException e) {}
    return String.format("商品【%s】500件", productId) ;
  }

  @GetMapping("/coupons/{userId}")
  public String getCoupons(@PathVariable String userId) {
    try {
      TimeUnit.SECONDS.sleep(3) ;
    } catch (InterruptedException e) {}
    return String.format("用户拥有【%s】6张优惠卷", userId) ;
  }
}