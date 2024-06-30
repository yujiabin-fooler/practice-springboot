package com.jiabin.redis.social.activities.practice;

public class User {
    private String id;
    private String name;
    // 省略构造函数、getter和setter方法


    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

