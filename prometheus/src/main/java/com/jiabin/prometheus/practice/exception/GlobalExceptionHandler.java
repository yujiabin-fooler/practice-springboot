package com.jiabin.prometheus.practice.exception;

import com.jiabin.prometheus.practice.monitor.PrometheusCustomMonitor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private PrometheusCustomMonitor monitor;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e) {
        monitor.getRequestErrorCount().increment();
        return "error, message: " + e.getMessage();
    }
}
