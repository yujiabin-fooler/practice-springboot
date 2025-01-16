package com.jiabin.aop.authority.practice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret ;

  @Value("${jwt.expiration}")
  private Long expiration ;

  // 生成 JWT 令牌
  public String generateToken(Long userId) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, String.valueOf(userId));
  }

  // 从令牌中获取用户名
  public Long getUserIdFromToken(String token) {
    return Long.valueOf(getClaimFromToken(token, Claims::getSubject));
  }

  // 从令牌中获取过期时间
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  // 检查令牌是否已过期
  public Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  // 从令牌中获取声明
  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // 获取令牌中的所有声明
  private Claims getAllClaimsFromToken(String token) {
    return (Claims) Jwts.parser()
      .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
      .build().parse(token).getPayload() ;
  }

  /**创建令牌*/
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().claims()
      .add(claims)
      .subject(subject)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
      .and()
      .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact() ;
  }
  
  public static void main(String[] args) {
    JwtUtil jwt = new JwtUtil() ;
    String token = jwt.generateToken(1L) ;
    System.out.println(token) ;
    Long userId = jwt.getUserIdFromToken(token) ;
    System.out.println(userId) ;
  }
}