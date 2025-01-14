package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.password.MatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    @Qualifier("customPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @PostMapping("/encode")
    public String encodePassword(@RequestBody MatchRequest matchRequest) {
        return passwordEncoder.encode(matchRequest.getRawPassword());
    }

    @PostMapping("/match")
    public boolean matchPasswords(@RequestBody MatchRequest matchRequest) {
        return passwordEncoder.matches(matchRequest.getRawPassword(), matchRequest.getEncodedPassword());
    }
}