package com.jiabin.mybatis.plus.multidatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiabin.mybatis.plus.multidatasource.entity.User;
import com.jiabin.mybatis.plus.multidatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> query() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        return userMapper.selectList(queryWrapper);
    }
}
