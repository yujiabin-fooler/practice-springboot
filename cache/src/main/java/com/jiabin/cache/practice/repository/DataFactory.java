package com.jiabin.cache.practice.repository;



import com.jiabin.cache.practice.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public class DataFactory {

    private DataFactory() {
    }

    private static List<UserDto> userDtoList;

    static {
        // 初始化集合
        userDtoList = new ArrayList<>();

        UserDto user = null;
        for (int i = 0; i < 10; i++) {
            user = new UserDto();
            user.setName("star" + i);
            user.setAge("2" + i);
            userDtoList.add(user);
        }
    }

    public static List<UserDto> getUserDaoList() {
        return userDtoList;
    }
}
