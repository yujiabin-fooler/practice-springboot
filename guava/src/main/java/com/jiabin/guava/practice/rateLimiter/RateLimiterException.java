package com.jiabin.guava.practice.rateLimiter;

public class RateLimiterException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RateLimiterException(String message) {
    super(message) ;
  }
}
