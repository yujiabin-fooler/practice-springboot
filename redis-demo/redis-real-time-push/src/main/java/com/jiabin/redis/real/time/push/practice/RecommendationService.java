package com.jiabin.redis.real.time.push.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void recordView(String userId, String productId) {
        // 记录用户查看的商品
        redisTemplate.opsForList().leftPush("user:" + userId + ":views", productId);
    }

    public List<String> recommendProducts(String userId) {
        // 简单推荐算法：返回用户查看次数最多的商品
        Set<String> viewedProducts = redisTemplate.opsForSet().members("user:" + userId + ":views");
        Map<String, Long> productViewCounts = new HashMap<>();
        viewedProducts.forEach(productId -> {
            long count = redisTemplate.opsForValue().decrement("user:" + userId + ":views:" + productId);
            productViewCounts.put(productId, count);
        });

        return productViewCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}