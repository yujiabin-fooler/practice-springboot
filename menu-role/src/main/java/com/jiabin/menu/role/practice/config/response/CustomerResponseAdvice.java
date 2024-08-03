package com.jiabin.menu.role.practice.config.response;

import com.jiabin.menu.role.practice.config.result.ResultMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 对controller层返回结果，进行增强拦截
 */
@ControllerAdvice
public class CustomerResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 是否开启支持advice功能
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 如果开启，就会对返回结果进行处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 设置响应类型为json
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(body != null && (body instanceof ResultMsg)){
            // 如果body返回的是ResultMsg类型的对象，不进行增强处理
            return body;
        }
        if(body != null && (body instanceof String)){
            // 如果body返回的是String类型的对象，单独处理
            return toJson(body);
        }
        return ResultMsg.success(body);
    }

    private Object toJson(Object body) {
        try {
            return new ObjectMapper().writeValueAsString(ResultMsg.success(body));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("无法转发json格式", e);
        }
    }
}
