package com.jiabin.schedule.quartz.group.practice.service.impl;

import com.jiabin.schedule.quartz.group.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @Description: 用户业务层实现类
 * @Author jiabin.yu
 * @Date: 2020/11/14
 */
@Slf4j
@Service("userService")
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    /**
     * 查询所有用户数量
     *
     * @return
     */
    @Override
    public int countAll() {
        int count = Math.abs(new Random().nextInt());
        log.debug("用户总数为: {}", count);
        return count;
    }
}
