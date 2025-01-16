package com.jiabin.dynamic.mgr.pracitce.service;

import org.springframework.stereotype.Service;

import com.jiabin.dynamic.mgr.pracitce.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository ;
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public void save() {
    this.userRepository.save() ; 
  }
  public void remove() {
    this.userRepository.remove() ;
  }
}
