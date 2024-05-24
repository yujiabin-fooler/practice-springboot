package com.jiabin.redis.practice.service;

import com.jiabin.redis.practice.api.RedisTemplateAPI;
import com.jiabin.redis.practice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用 redisTemplate 的操作实现类
 *
 */
@Service
public class UserService {

    @Autowired
    private RedisTemplateAPI redisTemplateAPI;

    public void save(User user) {
        redisTemplateAPI.setValue(user.getCode(), user, 10000);
    }

    public User get(String code) {
        User user = (User)redisTemplateAPI.getValue(code);

        return user;
    }

}