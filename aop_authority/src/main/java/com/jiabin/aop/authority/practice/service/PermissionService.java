package com.jiabin.aop.authority.practice.service;

import com.jiabin.aop.authority.practice.domain.Permission;
import com.jiabin.aop.authority.practice.domain.User;
import com.jiabin.aop.authority.practice.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

  private final PermissionRepository permissionRepository ;
  public PermissionService(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository ;
  }
  
  public List<Permission> findPermissions(Long userId) {
    return this.permissionRepository.findByUser(new User(userId)) ;
  }
}
