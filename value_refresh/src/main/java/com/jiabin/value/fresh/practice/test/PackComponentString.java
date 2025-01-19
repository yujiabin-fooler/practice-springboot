package com.jiabin.value.fresh.practice.test;

import org.springframework.stereotype.Component;

import com.jiabin.value.fresh.practice.annotation.PackValue;

import jakarta.annotation.PostConstruct;

@Component
public class PackComponentString {

  @PackValue("${pack.app.sno}")
  private String sno ;
  
  @PostConstruct
  public void init() {
    System.err.printf("PackComponentString: %d%n", this.sno) ;
  }

  public String getSno() {
    return sno;
  }
}
