package com.jiabin.redis.real.time.push.practice;


public class User {
    private String id;
    private String username;
    // 省略构造函数、getter和setter方法


    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}