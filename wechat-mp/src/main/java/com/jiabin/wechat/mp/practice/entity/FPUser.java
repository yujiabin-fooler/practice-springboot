package com.jiabin.wechat.mp.practice.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("fpassword_user")
public class FPUser {

    private Long id;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createTime;

}