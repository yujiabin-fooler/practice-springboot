package com.jiabin.shardingshpere.fundamental.practice.repository;

import com.jiabin.shardingshpere.fundamental.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
