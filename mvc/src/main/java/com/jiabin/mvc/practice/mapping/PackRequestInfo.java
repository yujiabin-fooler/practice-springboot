package com.jiabin.mvc.practice.mapping;

import java.lang.reflect.Method;

public class PackRequestInfo {

  private String path ;
  private Method method ;
  private Object handler ;
  public PackRequestInfo() {
  }
  public PackRequestInfo(String path, Method method, Object handler) {
    this.path = path;
    this.method = method;
    this.handler = handler;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public Method getMethod() {
    return method;
  }
  public void setMethod(Method method) {
    this.method = method;
  }
  public Object getHandler() {
    return handler;
  }
  public void setHandler(Object handler) {
    this.handler = handler;
  }
}
