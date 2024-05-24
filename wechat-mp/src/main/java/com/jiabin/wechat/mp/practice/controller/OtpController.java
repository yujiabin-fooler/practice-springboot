package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.dto.RequestOtpDTO;
import com.jiabin.wechat.mp.practice.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpUtil otpUtil;

    @GetMapping("/generate")
    public String generateOtp() {
        return otpUtil.generateOtp();
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestBody RequestOtpDTO reqOtp) {
        return otpUtil.validateOtp(reqOtp.getUserOtp())  ? "验证成功" : "验证失败" ;
    }
    
    @GetMapping("/testEq")
    public String testEq() {
    	String userOtp = otpUtil.generateOtp();
        return otpUtil.validateOtp(userOtp) ? "验证成功" : "验证失败";
    }
}
