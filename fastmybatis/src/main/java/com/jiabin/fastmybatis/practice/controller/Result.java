package com.jiabin.fastmybatis.practice.controller;


public class Result<T> {

    private static final Result<?> RESULT = new Result<>();

    private int code;
    private T data;
    private String msg;

    public static Result<?> ok() {
        return RESULT;
    }

    public static <E> Result<E> ok(E obj) {
        Result<E> result = new Result<>();
        result.setData(obj);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

