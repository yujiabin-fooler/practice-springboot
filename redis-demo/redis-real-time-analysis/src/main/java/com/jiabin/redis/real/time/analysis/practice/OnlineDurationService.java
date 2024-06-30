package com.jiabin.redis.real.time.analysis.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OnlineDurationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void updateUserOnlineDuration(String userId, long duration) {
        // 使用Sorted Set存储用户ID和在线时长
        redisTemplate.opsForZSet().incrementScore("user:online:duration", userId, duration);
    }

    public Set<String> getTopUsersByOnlineDuration(int topN) {
        // 获取在线时长最长的前N个用户
        Set<String> topUsers = redisTemplate.opsForZSet().reverseRange("user:online:duration", 0, topN - 1);
        return topUsers;
    }
}
