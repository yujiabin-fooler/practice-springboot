package com.jiabin.jwt.practice.exception;

/**
 * Created by macro on 2020/6/23.
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}
