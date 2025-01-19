package com.jiabin.trace.mdc.practice.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TraceXFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(TraceXFilter.class) ;
  public static final String TRACE_KEY = "traceXId" ;
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request ;
    // 通过请求header获取自定义的traceId，没有则系统生成
    String traceId = req.getHeader("x-trace") ;
    
    if (!StringUtils.hasLength(traceId)) {
      traceId = UUID.randomUUID().toString().replace("-", "").toUpperCase() ;
    }
    // 将当前请求的traceId存入到MDC中
    MDC.put(TRACE_KEY, traceId) ;
    
    logger.info("请求: {}", req.getServletPath()) ;
    try {
      chain.doFilter(request, response) ;
    } finally {
      MDC.remove(TRACE_KEY) ;
    }
  }
}