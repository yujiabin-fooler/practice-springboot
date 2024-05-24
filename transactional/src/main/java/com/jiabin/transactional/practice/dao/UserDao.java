package com.jiabin.transactional.practice.dao;

import com.jiabin.transactional.practice.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
* @Title: UserDao
* @Description:
* 用户数据接口 
* @Version:1.0.0
 */
@Mapper
public interface UserDao{
	
	
	  @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
	  void insert(User user) ;

	 @Update("update t_user set name=#{name}, age=#{age} where id=#{id}")
	  void update(User user) ;

	@Select("SELECT * FROM t_user where id=#{id}")
	 User findById(long id);

}
