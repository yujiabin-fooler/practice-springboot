package com.jiabin.springboot.dao.practice.user;

import com.jiabin.springboot.dao.practice.BaseDao;
import com.jiabin.springboot.model.practice.entity.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户模块DAO
 * @Author jiabin.yu
 * @Date: 2018/10/8
 */
@Repository
public interface UserDao extends BaseDao<UserDO> {


    /**
     * 账号注册校验
     *
     * @param userDO 用户信息
     * @return
     */
    int signUpCheck(UserDO userDO);



}
