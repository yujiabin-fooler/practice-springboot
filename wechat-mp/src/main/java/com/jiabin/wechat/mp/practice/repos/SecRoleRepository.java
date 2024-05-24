package com.jiabin.wechat.mp.practice.repos;

import com.jiabin.wechat.mp.practice.entity.SecRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecRoleRepository extends JpaRepository<SecRole, Long> {
    SecRole findByName(String name);
}