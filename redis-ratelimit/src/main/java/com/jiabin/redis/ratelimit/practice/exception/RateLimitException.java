package com.jiabin.redis.ratelimit.practice.exception;

public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }

}