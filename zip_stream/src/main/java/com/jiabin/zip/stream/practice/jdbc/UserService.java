package com.jiabin.zip.stream.practice.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import com.jiabin.zip.stream.practice.domain.User;

@Service
public class UserService {

  private final JdbcClient jdbcClient ;
  public UserService(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient ;
  }
  
  public List<User> list() {
    return this.jdbcClient
        .sql("select * from t_users")
        .query(User.class)
        .list() ;
  }
}
