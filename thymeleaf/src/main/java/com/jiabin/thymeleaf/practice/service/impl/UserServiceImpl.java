package com.jiabin.thymeleaf.practice.service.impl;

import com.jiabin.thymeleaf.practice.dao.UserDao;
import com.jiabin.thymeleaf.practice.pojo.User;
import com.jiabin.thymeleaf.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
* Title: UserServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	
	@Override
	public boolean addUser(User user) {
		boolean flag=false;
		try{
			userDao.addUser(user);
			flag=true;
		}catch(Exception e){
			System.out.println("新增失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) {
		boolean flag=false;
		try{
			userDao.updateUser(user);
			flag=true;
		}catch(Exception e){
			System.out.println("修改失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean flag=false;
		try{
			userDao.deleteUser(id);
			flag=true;
		}catch(Exception e){
			System.out.println("删除失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User findUserById(int id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
