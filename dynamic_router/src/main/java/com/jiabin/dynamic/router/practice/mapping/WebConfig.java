package com.jiabin.dynamic.router.practice.mapping;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jiabin.dynamic.router.practice.handler.UserHandler;

@Configuration
public class WebConfig {

  @Autowired
  public void setHandlerMapping(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping mapping, UserHandler handler)
      throws NoSuchMethodException {
    // 在Spring5.x版本需要配置下面的BuilderConfiguration;6.x以上不需要有默认的
//    BuilderConfiguration options = new BuilderConfiguration() ;
    // 该属性不能为空，该属性在构建RequestMappingInfo时需要PathPatternsRequestCondition对象，而PathPatternsRequestCondition对象
    // 是否有值是由是否有PathPatternParser决定
//    options.setPatternParser(new PathPatternParser()) ;
    RequestMappingInfo info = RequestMappingInfo.paths("/api/user/{id}").methods(RequestMethod.GET)
        /* .options(options) */.build();
    Method method = UserHandler.class.getMethod("getUser", Integer.class) ;
    mapping.registerMapping(info, handler, method);
  }
}