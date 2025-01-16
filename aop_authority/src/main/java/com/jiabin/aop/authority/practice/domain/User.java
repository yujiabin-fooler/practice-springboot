package com.jiabin.aop.authority.practice.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String username ;
  private String password ;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
  private Set<Permission> permissions = new HashSet<>() ;
  public User() {
  }
  public User(Long id) {
    this.id = id ;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Set<Permission> getPermissions() {
    return permissions;
  }
  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }
}
