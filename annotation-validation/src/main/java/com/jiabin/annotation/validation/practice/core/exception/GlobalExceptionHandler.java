package com.jiabin.annotation.validation.practice.core.exception;

import com.jiabin.annotation.validation.practice.core.result.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * GlobalExceptionHandler
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/5/24
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 拦截Controller层的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Exception e){
        LOGGER.error("【统一异常拦截】请求地址：{}, 错误信息：{}", request.getRequestURI(), e.getMessage());
        // 注解验证抛出的异常
        if(e instanceof MethodArgumentNotValidException){
            // 获取错误信息
            String error = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            return ResultMsg.fail(500, error);
        }
        // 自定义抛出的异常
        if(e instanceof CommonException){
            return ResultMsg.fail(((CommonException) e).getCode(), e.getMessage());
        }
        return ResultMsg.fail(999, e.getMessage());
    }
}
