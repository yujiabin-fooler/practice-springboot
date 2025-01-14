package com.jiabin.jetcache.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.jetcache.practice.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户表
 * 
 * @author junqiang.lu
 * @date 2020-08-31 14:09:53
 */
@Mapper
@Component("userDao")
public interface UserDao extends BaseMapper<UserEntity> {
	
}
