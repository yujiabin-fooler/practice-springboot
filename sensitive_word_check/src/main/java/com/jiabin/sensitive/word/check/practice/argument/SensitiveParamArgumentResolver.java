package com.jiabin.sensitive.word.check.practice.argument;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jiabin.sensitive.word.check.practice.annotation.Sensitive;
import com.jiabin.sensitive.word.check.practice.config.SensitiveWordProcessor;
import com.jiabin.sensitive.word.check.practice.exception.SensitiveException;

@Component
public class SensitiveParamArgumentResolver implements HandlerMethodArgumentResolver {

  private final SensitiveWordProcessor processor ;
  public SensitiveParamArgumentResolver(SensitiveWordProcessor processor) {
    this.processor = processor ;
  }
  
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(Sensitive.class) ;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    Sensitive sensitive = parameter.getParameterAnnotation(Sensitive.class) ;
    String name = parameter.getParameterName() ;
    String value = webRequest.getParameter(name) ;
    if (StringUtils.hasLength(value) && sensitive.check()) {
      boolean ret = this.processor.contains(value) ;
      if (ret) {
        boolean throwException = sensitive.throwException() ;
        if (throwException) {
          throw new SensitiveException("存在违规词: " + this.processor.findAll(value)) ;
        } else {
          value = this.processor.replace(value) ;
        }
      }
    }
    return value ;
  }
}
