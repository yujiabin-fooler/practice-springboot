package com.jiabin.guava.cache.practice.service;

import com.jiabin.guava.cache.practice.dto.UserDto;
import com.jiabin.guava.cache.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * UserService
 **/
@Service
@CacheConfig(cacheNames = "guavaCache") // 声明缓存的名称
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "#name")
    public UserDto getUserByName(String name) {
        System.out.println("从数据库中获取数据，而不是读取缓存");
        return userRepository.getUserByName(name);
    }

}
