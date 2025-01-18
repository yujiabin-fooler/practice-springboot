package com.jiabin.qulifier.practice.component;

import org.springframework.stereotype.Component;

import com.jiabin.qulifier.practice.annotation.PackQualifier;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Component
public class C {

  @Resource
//  @Qualifier
  @PackQualifier("a")
  private DAO dao ;

  @PostConstruct
  public void init() {
    System.out.println(this.dao) ;
  }
  
  @Override
  public String toString() {
    return "C [dao=" + dao + "]";
  }
}
