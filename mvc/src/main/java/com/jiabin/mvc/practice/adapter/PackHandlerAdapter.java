package com.jiabin.mvc.practice.adapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiabin.mvc.practice.annotation.PackBody;
import com.jiabin.mvc.practice.mapping.PackRequestInfo;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(-1)
public class PackHandlerAdapter implements HandlerAdapter, ApplicationContextAware {

  private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer() ;
  
  private ApplicationContext context ;
  private final ObjectMapper objectMapper ;
  private final ConversionService conversionService ;
  
  public PackHandlerAdapter(ObjectMapper objectMapper, ConversionService conversionService) {
    this.objectMapper = objectMapper ;
    this.conversionService = conversionService ;
  }
  
  @Override
  public boolean supports(Object handler) {
    return handler instanceof PackRequestInfo;
  }

  @Override
  public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    PackRequestInfo info = (PackRequestInfo) handler ;
    Object h = info.getHandler() ;
    if (h instanceof String beanName) {
      h = context.getBean(beanName) ;
    }
    invokeHandlerMethod(h, info, request, response) ;
    return null ;
  }
  
  private void invokeHandlerMethod(Object handler, PackRequestInfo info, HttpServletRequest request, HttpServletResponse response) {
    Method method = info.getMethod() ;
    method.setAccessible(true) ;
    
    // 通过反射获取参数名称
//    Parameter[] parameters = method.getParameters() ;
//    for (Parameter parameter : parameters) {
//      System.err.println(parameter.getName()) ;
//    }
    
    List<Object> args = getParameterValue(request, method) ;
    
    try {
      Object returnValue = method.invoke(handler, args.toArray()) ;
      response.setContentType("application/json;charset=utf-8") ;
      PrintWriter out = response.getWriter() ;
      out.println(this.objectMapper.writeValueAsString(returnValue)) ;
      out.close() ;
    } catch (Exception e) {
      throw new RuntimeException(e) ;
    }
  }

  private List<Object> getParameterValue(HttpServletRequest request, Method method) {
    Parameter[] parameters = method.getParameters() ;
    // String[] names = this.parameterNameDiscoverer.getParameterNames(method) ;
    List<Object> values = new ArrayList<>(parameters.length) ;
    if (parameters[0].isAnnotationPresent(PackBody.class)) {
      try {
        ServletInputStream stream = request.getInputStream() ;
        values.add(this.objectMapper.readValue(stream, parameters[0].getType())) ;
        return values ;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } 
    for (int i = 0, len = parameters.length; i < len; i++) {
      Parameter parameter = parameters[i] ;
      String name = parameter.getName() ;
      Class<?> type = parameter.getType() ;
      String source = request.getParameter(name) ;
      values.add(this.conversionService.convert(source, type)) ;
    }
    return values ;
  }
  
  @Override
  public long getLastModified(HttpServletRequest request, Object handler) {
    return 0 ;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context ;
  }
}
