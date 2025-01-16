package com.jiabin.jpa.lock.practice.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

  private String country;
  private String city;
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
}