package com.jiabin.kafaka.storm.practice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jiabin.kafaka.storm.practice.pojo.User;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Mapper
public interface UserDao {
	
	
	/**
	 * 批量新增据插入数据
	 * @param entityList
	 * @return
	 * @throws Exception
	 * @throws
	 */
	boolean insertBatch(List<User> entityList) throws Exception;
	
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> findByUser(User user) ;
    
}
