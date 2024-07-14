package com.jiabin.redis.lua.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @author jiabin.yu
 * @Description:
 */
@Controller
public class RedisController {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("/addKey")
    @ResponseBody
    public String addkey() {

        for (int i = 0; i < 500000; i++) {

            redisTemplate.opsForValue().set("test_key_" + i, i);
        }
        return null;
    }

}