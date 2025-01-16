package com.jiabin.aop.authority.practice.repository;

import com.jiabin.aop.authority.practice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  
  User findByUsernameAndPassword(String username, String password) ;
  
}
