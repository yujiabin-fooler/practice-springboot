package com.jiabin.log.practice.handler;

import org.springframework.web.bind.annotation.ResponseBody;

public class ProductHandler {
  
  @ResponseBody
  public String save() {
    return "保存商品成功" ;
  }
}
