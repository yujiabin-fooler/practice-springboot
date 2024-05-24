package com.jiabin.websocket.jwt.practice.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret-key}")
    private String secretKey; // 从配置文件读取密钥

    private static final long EXPIRATION_TIME = 3600000; // 1小时
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    // 创建JWT令牌
    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // 验证JWT令牌
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            // 无效的签名
        } catch (MalformedJwtException e) {
            // 无效的令牌
        } catch (ExpiredJwtException e) {
            // 令牌已过期
        } catch (UnsupportedJwtException e) {
            // 不支持的令牌
        } catch (IllegalArgumentException e) {
            // 无效的令牌
        }
        return false;
    }

    // 从令牌中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}