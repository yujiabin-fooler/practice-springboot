package com.jiabin.api.secret.signature.practice.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.jiabin.api.secret.signature.practice.annotation.Signature;
import com.jiabin.api.secret.signature.practice.crypto.SignatureResolver;
import com.jiabin.api.secret.signature.practice.utils.HashUtils;

@ControllerAdvice
public class SignatureResponseBodyAdivce implements ResponseBodyAdvice<Object> {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private static final String X_SIGNATURE_KEY = "X-Signature-Text" ;

	private final SignatureResolver resolver ;
	public SignatureResponseBodyAdivce(SignatureResolver resolver) {
	  this.resolver = resolver ;
	}
	
  @Override
  public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
    return methodParameter.hasMethodAnnotation(Signature.class)
        || methodParameter.getDeclaringClass().isAnnotationPresent(Signature.class);
  }

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
	  try {
      logger.error("签名原始内容: {}", HashUtils.genHashContent(body)) ;
    } catch (Exception e) {
      e.printStackTrace();
    }
		String signature = resolver.signature(body) ;
		response.getHeaders().add(X_SIGNATURE_KEY, signature) ;
		return body ;
	}
}
