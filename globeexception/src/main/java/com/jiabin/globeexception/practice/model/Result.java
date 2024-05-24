package com.jiabin.globeexception.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口统一返回
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求Status
     */
    private String code;

    /**
     * 业务信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * Instantiates a new Result.
     */
    public Result() {
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>("200", "请求成功", null);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> error(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> error(String code, String message) {
        return error(code, message, null);
    }

}

