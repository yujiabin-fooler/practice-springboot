package com.jiabin.wechat.mp.practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("otp_user")
public class OtpUser {
    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private Date createTime;
    private String secretKey;
}