package com.jiabin.websocket.jwt.practice.repository;

import com.jiabin.websocket.jwt.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.username <> ?1")
    List<User> findAllExceptCurrentUser(String username);
}