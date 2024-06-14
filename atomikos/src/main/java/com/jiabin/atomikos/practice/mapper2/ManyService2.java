package com.jiabin.atomikos.practice.mapper2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManyService2 {

    @Autowired
    private UserInfoMapper2 userInfoMapper2;

    @Transactional
    public int insert(String userId,String username, Integer age) {
        int i = userInfoMapper2.insert(userId,username, age);
        System.out.println("userInfoMapper2.insert end :" + null);
        int a = 1 / 0;//touch a error
        return i;
    }

}