package com.jiabin.lua.practice.service;//package com.jiabin.lua.practice.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.data.redis.core.script.RedisScript;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LuaScriptService {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    public Integer executeLuaScriptFromString() {
//        String luaScript = "local a = tonumber(ARGV[1])\nlocal b = tonumber(ARGV[2])\nreturn a + b";
//        RedisScript<Integer> script = new DefaultRedisScript<>(luaScript, Integer.class);
//        String[] keys = new String[0]; // 通常情况下，没有KEYS部分
//        Object[] args = new Object[]{10, 20}; // 传递给Lua脚本的参数
//        return stringRedisTemplate.execute(script, keys, args);
//
//    }
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    public Integer executeLuaScriptFromFile() {
//        Resource resource = resourceLoader.getResource("classpath:myscript.lua");
//        String luaScript;
//        try {
//            luaScript = new String(resource.getInputStream().readAllBytes());
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to read Lua script file.");
//        }
//
//        RedisScript<Integer> script = new DefaultRedisScript<>(luaScript, Integer.class);
//        String[] keys = new String[0]; // 通常情况下，没有KEYS部分
//        Object[] args = new Object[]{10, 20}; // 传递给Lua脚本的参数
//        return  stringRedisTemplate.execute(script, keys, args);
//
//    }
//
//
//}