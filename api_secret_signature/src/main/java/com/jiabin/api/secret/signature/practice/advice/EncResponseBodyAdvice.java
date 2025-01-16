package com.jiabin.api.secret.signature.practice.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiabin.api.secret.signature.practice.annotation.EncryptResponse;
import com.jiabin.api.secret.signature.practice.crypto.CryptResolver;

@ControllerAdvice
public class EncResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Value("${pack.secret.key:aaaabbbbccccdddd}")
  private String secret ;
  
  private final CryptResolver cryptResolver ;
  private final ObjectMapper objectMapper ;
  public EncResponseBodyAdvice(CryptResolver cryptResolver, ObjectMapper objectMapper) {
    this.cryptResolver = cryptResolver ;
    this.objectMapper = objectMapper ;
  }
  
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.hasMethodAnnotation(EncryptResponse.class) || 
        returnType.getDeclaringClass().isAnnotationPresent(EncryptResponse.class) ;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    if (body == null) {
      return body ;
    }
    try {
      String jsonStr = objectMapper.writeValueAsString(body) ;
      return cryptResolver.encrypt(secret, jsonStr) ;
    } catch (Exception e) {
      throw new RuntimeException(e) ;
    }
  }
}
