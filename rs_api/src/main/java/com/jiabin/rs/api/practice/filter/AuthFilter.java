package com.jiabin.rs.api.practice.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Priority;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

@Priority(-1)
public class AuthFilter implements ClientRequestFilter, ClientResponseFilter {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
    logger.info("AuthFilter已经获取到响应结果") ;
  }

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    logger.info("AuthFilter准备发送请求") ;
  }
}
