package com.jiabin.aop.authority.practice.interceptor;

import com.jiabin.aop.authority.practice.service.UserService;
import com.jiabin.aop.authority.practice.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
  
  private final static String TOKEN_KEY = "X-TOKEN" ;
  
  private final JwtUtil jwtUtil ;
  private final UserService userService ;
  public TokenInterceptor(JwtUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil ;
    this.userService = userService ;
  }
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(TOKEN_KEY) ;
    if (!StringUtils.hasLength(token)) {
      token = request.getParameter("token") ;
    }
    if (!StringUtils.hasLength(token)) {
      response.getWriter().println("Invalid token") ;
      return false ;
    }
    // 解析token;这里需要验证token
    Long userId = this.jwtUtil.getUserIdFromToken(token) ;
    SecurityContext.set(this.userService.queryUser(userId)) ;
    return true ;
  }
}
