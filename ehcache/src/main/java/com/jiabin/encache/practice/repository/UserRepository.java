package com.jiabin.encache.practice.repository;

import com.jiabin.encache.practice.dto.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
public class UserRepository {

    /**
     * 获取用户信息(此处是模拟的数据)
     */
    public User getUserByName(String username) {
        User user = getUserFromList(username);
        return user;
    }

    /**
     * 从模拟的数据集合中筛选 username 的数据
     */
    private User getUserFromList(String username) {

        List<User> userDaoList = DataFactory.getUserDaoList();
        for (User user : userDaoList) {
            if (Objects.equals(user.getName(), username)) {
                return user;
            }
        }
        return null;
    }
}
