package com.jiabin.wechat.mp.practice.password;

import lombok.Data;

@Data
public class MatchRequest {
    private String rawPassword;
    private String encodedPassword;
}