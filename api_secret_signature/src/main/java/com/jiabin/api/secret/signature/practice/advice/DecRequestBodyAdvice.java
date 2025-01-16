package com.jiabin.api.secret.signature.practice.advice;

import com.jiabin.api.secret.signature.practice.annotation.DecryptRequest;
import com.jiabin.api.secret.signature.practice.crypto.CryptResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class DecRequestBodyAdvice extends RequestBodyAdviceAdapter {

  @Value("${pack.secret.key:aaaabbbbccccdddd}")
  private String secret ;
  
  private final CryptResolver cryptResolver ;
  public DecRequestBodyAdvice(CryptResolver cryptResolver) {
    this.cryptResolver = cryptResolver ;
  }
  
  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return methodParameter.hasMethodAnnotation(DecryptRequest.class) 
        || methodParameter.getDeclaringClass().isAnnotationPresent(DecryptRequest.class) ;
  }
  
  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    String body = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8) ;
    String ret = this.cryptResolver.decrypt(secret, body) ;
    return new HttpInputMessage() {
      public HttpHeaders getHeaders() {
        return inputMessage.getHeaders() ;
      }
      public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(ret.getBytes(StandardCharsets.UTF_8)) ;
      }
    } ;
  }
}
