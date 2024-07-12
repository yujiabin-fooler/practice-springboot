package com.jiabin.jwt.practice.exception;

/**
 * @author jiabin.yu 2020/6/23.
 */
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}
