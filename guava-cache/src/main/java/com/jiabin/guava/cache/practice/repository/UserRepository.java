package com.jiabin.guava.cache.practice.repository;

import com.jiabin.guava.cache.practice.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * UserRepository
 **/
@Repository
public class UserRepository {

    /**
     * 获取用户信息(此处是模拟的数据)
     */
    public UserDto getUserByName(String username) {
        UserDto user = getUserFromList(username);
        return user;
    }

    /**
     * 从模拟的数据集合中筛选 username 的数据
     */
    private UserDto getUserFromList(String username) {

        List<UserDto> userDaoList = DataFactory.getUserDaoList();
        for (UserDto user : userDaoList) {
            if (Objects.equals(user.getName(), username)) {
                return user;
            }
        }
        return null;
    }
}
