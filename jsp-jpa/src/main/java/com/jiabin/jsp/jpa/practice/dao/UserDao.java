package com.jiabin.jsp.jpa.practice.dao;

import com.jiabin.jsp.jpa.practice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
//@Mapper
public interface UserDao extends JpaRepository<User, Long>{
	
}
