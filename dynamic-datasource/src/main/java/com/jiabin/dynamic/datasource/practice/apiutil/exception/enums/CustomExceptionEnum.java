package com.jiabin.dynamic.datasource.practice.apiutil.exception.enums;

import com.jiabin.dynamic.datasource.practice.apiutil.exception.CustomServiceException;
import com.jiabin.dynamic.datasource.practice.apiutil.model.BaseResponse;

/**
 * @author LDZ
 * @date 2020-03-02 17:23
 */
public enum CustomExceptionEnum {


    /**
     * 业务的参数错误
     */
    PARAM_ERROR(105) {
        // override

    };


    int code;

    public RuntimeException handlerException(String s, Object d) {
        return new CustomServiceException(this, s, d);
    }

    public RuntimeException handlerException(String s) {
        return new CustomServiceException(this, s);
    }

    public BaseResponse handlerBaseResponse(String s, Object d) {
        return new BaseResponse(this, s, d);
    }

    public BaseResponse handlerBaseResponse(String s) {
        return new BaseResponse(this, s);
    }

    public int getCode() {
        return code;
    }

    CustomExceptionEnum(int code) {
        this.code = code;
    }
}
