package com.jiabin.mybatis.plus.multidatasource.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiabin.mybatis.plus.multidatasource.entity.UserInfo;
import com.jiabin.mybatis.plus.multidatasource.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 3使用@DS切换数据源 中已展示过该类
 */
@DS("slave")
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> query() {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        return userInfoMapper.selectList(queryWrapper);
    }
}
