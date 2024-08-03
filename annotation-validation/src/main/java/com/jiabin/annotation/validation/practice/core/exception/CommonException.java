package com.jiabin.annotation.validation.practice.core.exception;

/**
 * <p>
 * CommonException
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/5/27
 */
public class CommonException extends RuntimeException {

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
