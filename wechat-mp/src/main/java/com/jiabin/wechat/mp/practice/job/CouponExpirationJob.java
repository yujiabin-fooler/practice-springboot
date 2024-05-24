package com.jiabin.wechat.mp.practice.job;

import com.jiabin.wechat.mp.practice.entity.Coupon;
import com.jiabin.wechat.mp.practice.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponExpirationJob {
    @Autowired
    private CouponService couponService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0 0 * * * *") // 每小时执行一次
    public void checkExpiredCoupons() {
        List<Coupon> expiredCoupons = couponService.getExpiredCoupons();

        // 从缓存中移除过期数据
        for (Coupon expiredCoupon : expiredCoupons) {
            redisTemplate.opsForList().remove("coupons", 0, expiredCoupon);
        }
    }
}