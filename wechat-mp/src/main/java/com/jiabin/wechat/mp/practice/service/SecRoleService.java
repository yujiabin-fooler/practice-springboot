package com.jiabin.wechat.mp.practice.service;

import com.jiabin.wechat.mp.practice.entity.SecRole;
import com.jiabin.wechat.mp.practice.repos.SecRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecRoleService {
    @Autowired
    private SecRoleRepository roleRepository;
    
    public SecRole findByName(String name) {
        return roleRepository.findByName(name);
    }
}