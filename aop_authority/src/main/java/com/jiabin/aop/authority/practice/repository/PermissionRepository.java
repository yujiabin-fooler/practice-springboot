package com.jiabin.aop.authority.practice.repository;

import com.jiabin.aop.authority.practice.domain.Permission;
import com.jiabin.aop.authority.practice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

  List<Permission> findByUser(User user) ;
  
}
