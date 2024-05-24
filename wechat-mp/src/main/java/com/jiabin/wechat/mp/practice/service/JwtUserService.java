package com.jiabin.wechat.mp.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiabin.wechat.mp.practice.entity.JwtUser;
import com.jiabin.wechat.mp.practice.mapper.JwtUserMapper;
import org.springframework.stereotype.Service;

@Service
public class JwtUserService{

    private final JwtUserMapper jwtUserMapper;

    public JwtUserService(JwtUserMapper jwtUserMapper) {
        this.jwtUserMapper = jwtUserMapper;
    }

    public JwtUser getUserDetailsByUsername(String username) {
        // 使用QueryWrapper构造查询条件
        QueryWrapper<JwtUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);

        // 使用MyBatis-Plus查询用户信息
        return jwtUserMapper.selectOne(queryWrapper);
    }
}