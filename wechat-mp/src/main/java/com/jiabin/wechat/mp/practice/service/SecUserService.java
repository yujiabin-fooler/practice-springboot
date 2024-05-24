package com.jiabin.wechat.mp.practice.service;

import com.jiabin.wechat.mp.practice.entity.SecUser;
import com.jiabin.wechat.mp.practice.repos.SecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecUserService {
    @Autowired
    private SecUserRepository userRepository;
    
    public SecUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}