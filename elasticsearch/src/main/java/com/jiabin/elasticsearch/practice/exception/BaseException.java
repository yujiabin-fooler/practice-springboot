package com.jiabin.elasticsearch.practice.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected abstract HttpStatus getStatus();
}
