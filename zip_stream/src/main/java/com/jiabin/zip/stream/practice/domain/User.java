package com.jiabin.zip.stream.practice.domain;

public class User {

  private String id ;
  private String username ;
  private String password ;
  private Integer enabled ;
  private Integer locked ;
  public String getId() {
    return id;
  }
  public void setId(String id) {
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
  public Integer getEnabled() {
    return enabled;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  public Integer getLocked() {
    return locked;
  }
  public void setLocked(Integer locked) {
    this.locked = locked;
  }
  @Override
  public String toString() {
    return "[id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + ", locked="
        + locked + "]";
  }
}
