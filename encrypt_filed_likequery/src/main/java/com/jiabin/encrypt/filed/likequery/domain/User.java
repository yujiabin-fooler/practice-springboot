package com.jiabin.encrypt.filed.likequery.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jiabin.encrypt.filed.likequery.handler.EntityAttributeConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user")
@EntityListeners({UserListener.class})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String name ;
  @Convert(converter = EntityAttributeConverter.class)
  private String phone ;
  @Convert(converter = EntityAttributeConverter.class)
  private String idNo ;
  private String addr ;
  @JsonIgnore
  private String keyLikePhone ;
  @JsonIgnore
  private String keyLikeIdNo ;
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
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getIdNo() {
    return idNo;
  }
  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }
  public String getAddr() {
    return addr;
  }
  public void setAddr(String addr) {
    this.addr = addr;
  }
  public String getKeyLikePhone() {
    return keyLikePhone;
  }
  public void setKeyLikePhone(String keyLikePhone) {
    this.keyLikePhone = keyLikePhone;
  }
  public String getKeyLikeIdNo() {
    return keyLikeIdNo;
  }
  public void setKeyLikeIdNo(String keyLikeIdNo) {
    this.keyLikeIdNo = keyLikeIdNo;
  }
}
