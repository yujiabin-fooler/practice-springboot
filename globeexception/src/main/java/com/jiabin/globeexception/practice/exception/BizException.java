package com.jiabin.globeexception.practice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 自定义业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final String errorCode;
    /**
     * 错误信息
     */
    private final String errorMessage;

    public BizException(String errorCode, String errorMessage) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
