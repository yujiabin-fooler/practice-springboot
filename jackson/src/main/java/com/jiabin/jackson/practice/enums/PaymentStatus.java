package com.jiabin.jackson.practice.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {

  NO_PAY(0, "未支付"), PAID(1, "已支付") ;
  
  PaymentStatus(int code, String desc) {
    this.code = code ;
    this.desc = desc ;
  }
  private Integer code ;
  @JsonValue
  private String desc ;
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public String getDesc() {
    return desc;
  }
  public void setDesc(String desc) {
    this.desc = desc;
  }
  
}
