package com.jiabin.wechat.mp.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.wechat.mp.practice.entity.JwtUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JwtUserMapper extends BaseMapper<JwtUser> {
    // 可自定义查询方法
}