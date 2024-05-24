package com.jiabin.encache.practice.service;


import com.jiabin.encache.practice.dto.User;
import com.jiabin.encache.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "#name")
    public User getUser(String name) {
        System.out.println("从数据库中获取数据，而不是读取缓存");
        User user = userRepository.getUserByName(name);

        return user;
    }
}
