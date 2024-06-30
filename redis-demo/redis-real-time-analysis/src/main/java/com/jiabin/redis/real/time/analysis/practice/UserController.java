package com.jiabin.redis.real.time.analysis.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private OnlineDurationService onlineDurationService;

    @PostMapping("/user/{userId}/login")
    public ResponseEntity<String> userLogin(@PathVariable String userId) {
        // 用户登录逻辑，可以是任何触发登录的事件
        return ResponseEntity.ok("User " + userId + " logged in");
    }

    @PostMapping("/user/{userId}/logout")
    public ResponseEntity<String> userLogout(@PathVariable String userId) {
        // 用户登出时记录在线时长
        long duration = 111111111;
        // 计算用户在线时长的逻辑
        onlineDurationService.updateUserOnlineDuration(userId, duration);

        return ResponseEntity.ok("User " + userId + " logged out");
    }
}
