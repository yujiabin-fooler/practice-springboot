package com.jiabin.encrypt.filed.likequery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.encrypt.filed.likequery.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByKeyLikeIdNoContaining(String keyLikeIdNo) ;
  
}
