package com.jiabin.multidatasource.practice.service.impl;

import com.jiabin.multidatasource.practice.dao.BaseDao;
import com.jiabin.multidatasource.practice.dao.master.UserDao;
import com.jiabin.multidatasource.practice.pojo.User;
import com.jiabin.multidatasource.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* Title: UserServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getMapper() {
		return this.userDao;
	}
	
}
