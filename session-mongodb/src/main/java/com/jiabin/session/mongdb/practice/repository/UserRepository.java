package com.jiabin.session.mongdb.practice.repository;

import com.jiabin.session.mongdb.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}