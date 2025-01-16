package com.jiabin.encrypt.filed.likequery.mapper;

import java.util.List;

import com.jiabin.encrypt.filed.likequery.domain.User;

public interface UserMapper {

  List<User> queryUsers(String idNo) ;
  
}
