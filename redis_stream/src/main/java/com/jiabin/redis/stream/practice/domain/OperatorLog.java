package com.jiabin.redis.stream.practice.domain;

import java.util.Date;

public class OperatorLog {

  /**操作模块*/
  private String module ;
  /**操作动作*/
  private String operator ;
  /**操作人*/
  private String name ;
  /**操作时间*/
  private Date time ;
  public OperatorLog() {
  }
  
  public OperatorLog(String module, String operator, String name, Date time) {
    this.module = module;
    this.operator = operator;
    this.name = name;
    this.time = time;
  }

  public String getModule() {
    return module;
  }
  public void setModule(String module) {
    this.module = module;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Date getTime() {
    return time;
  }
  public void setTime(Date time) {
    this.time = time;
  }
}
