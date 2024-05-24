package com.jiabin.wechat.mp.practice.init;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiabin.wechat.mp.practice.entity.JwtUser;
import com.jiabin.wechat.mp.practice.mapper.JwtUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JwtUserInit implements CommandLineRunner {

	@Autowired
    private JwtUserMapper jwtUserMapper;
	
	@Autowired
	@Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 初始化 admin 用户
        String username = "admin";
        String password = "123456";
        QueryWrapper<JwtUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        if (jwtUserMapper.selectOne(queryWrapper) == null) {
            JwtUser user = new JwtUser();
            user.setUserName(username);
            user.setPassword(passwordEncoder.encode(password)); // 使用 BCrypt 对密码进行加密
            user.setNickName("管理员");
            user.setCreateTime(LocalDateTime.now());
            jwtUserMapper.insert(user);
        }
    }
}
