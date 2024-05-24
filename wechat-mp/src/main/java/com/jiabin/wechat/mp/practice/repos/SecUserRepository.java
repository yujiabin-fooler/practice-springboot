package com.jiabin.wechat.mp.practice.repos;

import com.jiabin.wechat.mp.practice.entity.SecUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long> {
    SecUser findByUsername(String username);
}