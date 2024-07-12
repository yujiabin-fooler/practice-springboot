package com.jiabin.valid.params.practice.exception;


public class ParamsErrorException extends RuntimeException {

    private final int code;

    public ParamsErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}