package com.jiabin.globeexception.practice.exception;

import com.jiabin.globeexception.practice.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param req the req
     * @param e   the e
     * @return result
     */
    @ExceptionHandler(value = BizException.class)
    public Result<BizException> bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("[ {} ] {} 请求异常: {}", req.getMethod(), req.getRequestURL(), e.getErrorCode());
        return Result.error(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 参数异常信息返回
     *
     * @param req the req
     * @param e   the e
     * @return result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Map<String, String>> methodArgumentNotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        log.error("[ {} ] {} 请求参数校验错误", req.getMethod(), req.getRequestURL());
        Map<String, String> paramExceptionInfo = new TreeMap<>();
        for (ObjectError objectError : allErrors) {
            FieldError fieldError = (FieldError) objectError;
            log.error("参数 {} = {} 校验错误：{}", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
            paramExceptionInfo.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return Result.error(HttpStatus.BAD_REQUEST.toString(), "PARAM_EXCEPTION", paramExceptionInfo);
    }

    /**
     * 参数异常信息返回
     *
     * @param req the req
     * @param e   the e
     * @return result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<String> constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) {
        log.error("[ {} ] {} 请求参数校验错误", req.getMethod(), req.getRequestURL());
        return Result.error(HttpStatus.BAD_REQUEST.toString(), "PARAM_EXCEPTION", e.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param req the req
     * @param e   the e
     * @return result
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("[ {} ] {} 未定义异常: {}", req.getMethod(), req.getRequestURL(), e.getMessage());
        return Result.error("500", e.getMessage());
    }
}
