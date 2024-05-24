package com.jiabin.wechat.mp.practice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createTime;
}
