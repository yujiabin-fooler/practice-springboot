package com.jiabin.aop.authority.practice.service;

import com.jiabin.aop.authority.practice.domain.User;
import com.jiabin.aop.authority.practice.repository.UserRepository;
import com.jiabin.aop.authority.practice.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository ;
  private final JwtUtil jwtUtil ;
  public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
    this.userRepository = userRepository ;
    this.jwtUtil = jwtUtil ;
  }
  
  public String login(String username, String password) {
    User user = this.userRepository.findByUsernameAndPassword(username, password) ;
    if (user == null) {
      throw new RuntimeException("用户名或密码错误") ;
    }
    return jwtUtil.generateToken(user.getId()) ;
  }
  
  public User queryUser(Long userId) {
    return this.userRepository.findById(userId).orElse(null) ;
  }
}
