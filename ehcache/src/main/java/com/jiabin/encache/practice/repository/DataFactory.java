package com.jiabin.encache.practice.repository;


import com.jiabin.encache.practice.dto.User;

import java.util.ArrayList;
import java.util.List;


public class DataFactory {

    private DataFactory() {
    }

    private static List<User> userList;

    static {
        // 初始化集合
        userList = new ArrayList<>();

        User user = null;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setName("star" + i);
            user.setAge(23);
            userList.add(user);
        }
    }

    public static List<User> getUserDaoList() {
        return userList;
    }
}
