package com.jiabin.jackson.practice.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pack.jackson.custom_annotation.Sensitive;

public class User {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id ;
//  @JsonValue
  private String name ;
  @Sensitive(start = 6, end = 4)
  private String idCard ;
  @Sensitive(start = 4, end = 3)
  private String phone ;
  
  public User(Long id, String name, String idCard, String phone) {
    this.id = id;
    this.name = name;
    this.idCard = idCard;
    this.phone = phone;
  }
  public User() {
  }
  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getIdCard() {
    return idCard;
  }
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
}
