package com.jiabin.session.jdbc.practice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}