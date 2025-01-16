package com.jiabin.api.secret.signature.practice.domain;

import java.util.Date;

public class User {

  private Long id ;
  private String name ;
  private Integer age ;
  private Date birthday = new Date();
  public User() {
  }
  public User(Long id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
  
  public User(Long id, String name, Integer age, Date birthday) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.birthday = birthday;
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
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
}
