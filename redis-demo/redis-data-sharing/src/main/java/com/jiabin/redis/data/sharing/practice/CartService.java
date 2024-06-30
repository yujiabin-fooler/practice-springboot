package com.jiabin.redis.data.sharing.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addToCart(String cartId, String productId, int quantity) {
        // 将购物车项存储到Redis的Hash结构中
        redisTemplate.opsForHash().put("cart:" + cartId, productId, quantity);
    }

    public Map<Object, Object> getCart(String cartId) {
        // 从Redis获取购物车内容
        return redisTemplate.opsForHash().entries("cart:" + cartId);
    }
}


