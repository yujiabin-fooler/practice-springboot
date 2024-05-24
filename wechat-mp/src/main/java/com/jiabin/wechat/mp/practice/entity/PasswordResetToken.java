package com.jiabin.wechat.mp.practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("password_reset_token")
public class PasswordResetToken {
	
    private Long id;
    private String token;
    private Long userId;
    private LocalDateTime expiryDate;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}