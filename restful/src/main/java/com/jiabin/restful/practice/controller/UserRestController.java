package com.jiabin.restful.practice.controller;

import com.jiabin.restful.practice.pojo.User;
import com.jiabin.restful.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 
* Title: UserRestController
* Description: 
* 用户控制层
* Version:1.0.0
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    private UserService userService;
 
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
    	System.out.println("开始新增...");
        return userService.addUser(user);
    }
    
	@RequestMapping(value = "/userUpdate", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
    	System.out.println("开始更新...");
        return userService.updateUser(user);
    }
	
	@RequestMapping(value = "/userDelete", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "userId", required = true) int userId) {
    	System.out.println("开始删除...");
        return userService.deleteUser(userId);
    }
	
    @RequestMapping(value = "/userQuery", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
    	System.out.println("开始查询...");
        return userService.findUserByName(userName);
    }
    
    
    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public List<User> findByUserAge() {
    	System.out.println("开始查询所有数据...");
        return userService.findAll();
    }
}
