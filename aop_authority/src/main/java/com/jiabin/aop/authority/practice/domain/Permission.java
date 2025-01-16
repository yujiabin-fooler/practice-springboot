package com.jiabin.aop.authority.practice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "t_permission")
public class Permission {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String name ;
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "uid")
  private User user ;
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
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
