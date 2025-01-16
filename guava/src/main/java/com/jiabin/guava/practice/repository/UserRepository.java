package com.jiabin.guava.practice.repository;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.jiabin.guava.practice.domain.User;

@Repository
public class UserRepository {

  public User findById(Long id) {
    return new User(id, "姓名 - " + id, new Random().nextInt(100)) ;
  }
}
