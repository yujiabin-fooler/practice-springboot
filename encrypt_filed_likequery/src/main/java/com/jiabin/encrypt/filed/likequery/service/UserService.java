package com.jiabin.encrypt.filed.likequery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jiabin.encrypt.filed.likequery.domain.User;
import com.jiabin.encrypt.filed.likequery.mapper.UserMapper;
import com.jiabin.encrypt.filed.likequery.repository.UserRepository;
import com.jiabin.encrypt.filed.likequery.utils.LikeQueryUtils;

@Service
public class UserService {

  private final UserRepository userRepository ;
  private final UserMapper userMapper ;
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }
  
  public User save(User user) {
    return this.userRepository.saveAndFlush(user) ;
  }
  
  public User findById(Long id) {
    return this.userRepository.findById(id).orElse(null) ;
  }
  
  public List<User> queryUserByIdNo(String idNo) {
    return this.userRepository.findByKeyLikeIdNoContaining(LikeQueryUtils.encrypt(idNo)) ;
  }
  
  public List<User> queryUsers(String idNo) {
    return this.userMapper.queryUsers(LikeQueryUtils.encrypt(idNo)) ;
  }
}
