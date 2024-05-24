package com.jiabin.mybatisplus.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.mybatisplus.practice.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}