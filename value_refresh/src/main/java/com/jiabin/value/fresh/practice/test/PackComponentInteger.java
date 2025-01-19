package com.jiabin.value.fresh.practice.test;

import org.springframework.stereotype.Component;

import com.jiabin.value.fresh.practice.annotation.PackValue;

import jakarta.annotation.PostConstruct;

@Component
public class PackComponentInteger {

  @PackValue("${pack.app.sno}")
  private Integer sno ;
  
  @PostConstruct
  public void init() {
    System.err.printf("PackComponentInteger: %d%n", this.sno) ;
  }

  public Integer getSno() {
    return sno;
  }
}
