package com.jiabin.menu.role.practice.config.exception;

public class CommonException extends RuntimeException {

    /**错误码*/
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CommonException(String message) {
        super(message);
        this.code = 500;
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
