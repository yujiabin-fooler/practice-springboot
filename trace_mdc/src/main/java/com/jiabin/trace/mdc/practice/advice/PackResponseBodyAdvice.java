package com.jiabin.trace.mdc.practice.advice;

import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiabin.trace.mdc.practice.dto.R;
import com.jiabin.trace.mdc.practice.filter.TraceXFilter;

import jakarta.annotation.Resource;

@RestControllerAdvice
public class PackResponseBodyAdvice implements ResponseBodyAdvice<Object> {
  @Resource
  private ObjectMapper objectMapper ;  
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return !returnType.getParameterType().equals(R.class)  ;
  }
  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    if (body instanceof String) {
      try {
        return this.objectMapper.writeValueAsString(R.success(response, MDC.get(TraceXFilter.TRACE_KEY))) ;
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
    if (body instanceof ResponseEntity<?> entity) {
      return R.success(entity.getBody(), MDC.get(TraceXFilter.TRACE_KEY)) ;
    }
    return R.success(body, MDC.get(TraceXFilter.TRACE_KEY)) ;
  }
}