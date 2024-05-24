package com.jiabin.redis.mybatis.plus.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.redis.mybatis.plus.practice.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
	
}