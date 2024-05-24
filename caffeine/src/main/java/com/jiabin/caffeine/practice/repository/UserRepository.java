package com.jiabin.caffeine.practice.repository;

import com.jiabin.caffeine.practice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}