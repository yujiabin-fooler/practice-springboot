package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.entity.JwtUser;
import com.jiabin.wechat.mp.practice.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Optional;

@RestController 
@RequestMapping("/api/jwt")
public class JwtLoginController {

	private final AuthService authService;

    @Autowired
    public JwtLoginController(AuthService authService) {
        this.authService = authService;
    }
    
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody JwtUser user) throws JOSEException {
        Optional<JwtUser> authenticatedUser = authService.verifyUser(user.getUserName(), user.getPassword());

        if (authenticatedUser.isPresent()) {
            // 用户验证成功，生成 JWT 并返回给客户端
            String token = authService.generateJWT(authenticatedUser.get());
            return ResponseEntity.ok(token);
        } else {
            // 用户验证失败，返回 401 未授权状态码
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@GetMapping("/user/info")
    public ResponseEntity<JwtUser> getUserInfo(HttpServletRequest request) throws ParseException, JOSEException {
        // 从请求头部获取 JWT Token
        String token = request.getHeader("Authorization");

        // 验证 JWT Token 是否有效
        Optional<JwtUser> authenticatedUser = authService.verifyJWT(token);

        // 如果验证通过，则返回用户信息
        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok(authenticatedUser.get());
        } else {
            // 验证失败，返回 401 未授权状态码
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
