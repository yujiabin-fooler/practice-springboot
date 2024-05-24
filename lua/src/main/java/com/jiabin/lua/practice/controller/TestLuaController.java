package com.jiabin.lua.practice.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestLuaController {


    @Resource
    private DefaultRedisScript<Boolean> redisScript;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lua")
    public ResponseEntity lua() {
        List<String> keys = Arrays.asList("testLua", "hello lua");
        Boolean execute = stringRedisTemplate.execute(redisScript, keys, "100");
        assert execute != null;
        return ResponseEntity.ok(execute);
    }

}

