package com.jiabin.redis.mybatis.plus.practice.controller;

import com.jiabin.redis.mybatis.plus.practice.dto.CouponRequestDTO;
import com.jiabin.redis.mybatis.plus.practice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/generate-coupon")
    public void generateCoupon(@RequestBody CouponRequestDTO request) {
        couponService.generateCoupon(request.getCode(), request.getValue(), request.getExpiryDate());
    }
    
    @GetMapping("/check-coupon/{code}")
    public String checkCouponExpiration(@PathVariable String code) {
        boolean isExpired = couponService.isCouponExpired(code);
    
        if (isExpired) {
            return "优惠卷 " + code + " 已过期.";
        } else {
            return "优惠卷 " + code + " 未过期.";
        }
    }

}