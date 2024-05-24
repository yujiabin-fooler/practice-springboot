package com.jiabin.wechat.mp.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiabin.wechat.mp.practice.entity.FPUser;
import com.jiabin.wechat.mp.practice.mapper.FPUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FPUserService extends ServiceImpl<FPUserMapper, FPUser> {
    private final FPUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FPUserService(FPUserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public FPUser findByUserName(String userName) {
    	  // 根据用户名查询用户
        QueryWrapper<FPUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return userMapper.selectOne(queryWrapper);
    }
    
    public FPUser findByEmail(String email) {
    	// 根据用户名查询用户
    	QueryWrapper<FPUser> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("email", email);
    	return userMapper.selectOne(queryWrapper);
    }

    public void updateUserPassword(Long userId, String newPassword) {
        FPUser user = userMapper.selectById(userId);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userMapper.updateById(user);
        }
    }
}