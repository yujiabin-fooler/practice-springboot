package com.jiabin.wechat.mp.practice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiabin.wechat.mp.practice.entity.PasswordResetToken;
import com.jiabin.wechat.mp.practice.mapper.PasswordResetTokenMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetTokenService extends ServiceImpl<PasswordResetTokenMapper, PasswordResetToken> {

    public void createPasswordResetToken(Long userId, String token, LocalDateTime expiryDate) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUserId(userId);
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(expiryDate);
        this.save(passwordResetToken);
    }

    public PasswordResetToken findByToken(String token) {
    	QueryWrapper<PasswordResetToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        return this.getOne(queryWrapper);
    }

    public void deleteToken(Long tokenId) {
        this.removeById(tokenId);
    }
}
