package com.jiabin.fastmybatis.practice.service;

import com.gitee.fastmybatis.core.support.CommonService;
import com.jiabin.fastmybatis.practice.mapper.TUserMapper;
import com.jiabin.fastmybatis.practice.model.TUser;
import org.springframework.stereotype.Service;

@Service
//public class UserService extends BaseService<TUser/*实体类*/, Integer/*主键类型*/, TUserMapper> {
public class UserService implements CommonService<TUser/*实体类*/, Integer/*主键类型*/, TUserMapper> {


}
