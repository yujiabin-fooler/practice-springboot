package com.jiabin.multidatasource.practice.dao.master;

import com.jiabin.multidatasource.practice.dao.BaseDao;
import com.jiabin.multidatasource.practice.pojo.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface UserDao extends BaseDao<User> {
    
}
