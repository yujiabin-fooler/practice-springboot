package com.jiabin.rs.api.practice.filter;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Priority;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

@Priority(0)
public class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
    logger.info("已经得到响应结果, 响应header: {}", responseContext.getHeaders()) ;
  }

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    logger.info("准备发送请求, 请求Headers: {}", requestContext.getHeaders()) ;
    requestContext.getHeaders().replace("x-token", List.of("88888888888")) ;
  }
}
