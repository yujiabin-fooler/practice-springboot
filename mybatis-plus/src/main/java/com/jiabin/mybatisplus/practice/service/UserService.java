package com.jiabin.mybatisplus.practice.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jiabin.mybatisplus.practice.entity.User;
import com.jiabin.mybatisplus.practice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(String name) {
        User user = new User();
        user.setName(name);

        userMapper.insert(user);

        return "操作成功";
    }

    public List<User> query() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        return userMapper.selectList(queryWrapper);
    }

    public String update(int id, String name) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getName, name);
        updateWrapper.eq(User::getId, id);
        User user = new User();
        userMapper.update(user,updateWrapper);

        return "操作成功";
    }

    public String delete(int id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);

        userMapper.delete(queryWrapper);

        return "操作成功";
    }
}