package com.jiabin.mapstruct.practice.exception;

/**
 * 商品验证异常类
 */
public class ProductValidatorException extends Exception{
    public ProductValidatorException(String message) {
        super(message);
    }
}
