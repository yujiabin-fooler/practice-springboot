package com.jiabin.data.desensitization.practice.controller;

import com.jiabin.data.desensitization.practice.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * HelloController
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public UserEntity hello() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("张三");
        userEntity.setMobile("18000000001");
        userEntity.setIdCard("420117200001011000008888");
        userEntity.setAge(20);
        userEntity.setSex("男");
        return userEntity;
    }
}
