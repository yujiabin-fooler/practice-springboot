package com.jiabin.redis.data.out.of.date.practice;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;


@Controller
public class SessionController {

    // 用户登录后，Spring Session会自动存储会话信息到Redis
    @PostMapping("/login")
    public String login(SessionStatus sessionStatus, String username) {
//        sessionStatus.setAttribute("username", username);

        return "loginSuccess";
    }

    // 用户登出时，清除会话信息
    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
//        sessionStatus.invalidate();
        return "logoutSuccess";
    }
}
