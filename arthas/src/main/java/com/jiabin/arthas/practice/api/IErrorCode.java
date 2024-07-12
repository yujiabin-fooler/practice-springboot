package com.jiabin.arthas.practice.api;

/**
 * 封装API的错误码
 * @author jiabin.yu 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
