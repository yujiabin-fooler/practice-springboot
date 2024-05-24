package com.jiabin.elasticsearch.practice.exception;

import org.springframework.http.HttpStatus;


public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
