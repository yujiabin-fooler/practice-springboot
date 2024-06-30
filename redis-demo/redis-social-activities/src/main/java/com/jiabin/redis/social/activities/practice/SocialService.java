package com.jiabin.redis.social.activities.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;


@Service
public class SocialService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addFriend(String userOneId, String userTwoId) {
        // 使用集合存储用户的好友列表
        redisTemplate.opsForSet().add("friends:" + userOneId, userTwoId);
        redisTemplate.opsForSet().add("friends:" + userTwoId, userOneId);
    }

    public Set<String> getFriends(String userId) {
        // 获取用户的好友列表
        return redisTemplate.opsForSet().members("friends:" + userId);
    }

    public void postStatusUpdate(String userId, String content) {
        // 使用列表存储用户的状态更新时间线
        StatusUpdate statusUpdate = new StatusUpdate(userId, content, Instant.now());
        redisTemplate.opsForList().rightPush("timeline:" + userId, String.valueOf(statusUpdate));
    }

    public List<String> getStatusUpdates(String userId) {
        // 获取用户的状态更新时间线
        return redisTemplate.opsForList().range("timeline:" + userId, 0, -1);
    }
}