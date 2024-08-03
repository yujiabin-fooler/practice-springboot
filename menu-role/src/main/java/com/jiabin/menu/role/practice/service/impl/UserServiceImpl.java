package com.jiabin.menu.role.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiabin.menu.role.practice.dao.UserMapper;
import com.jiabin.menu.role.practice.entity.User;
import com.jiabin.menu.role.practice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
