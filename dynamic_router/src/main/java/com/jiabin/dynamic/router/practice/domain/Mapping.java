package com.jiabin.dynamic.router.practice.domain;

public class Mapping {

  private String path ;
  private String requestMethod ;
  private String beanName ;
  private String methodName ;
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getBeanName() {
    return beanName;
  }
  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }
  public String getMethodName() {
    return methodName;
  }
  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }
  public String getRequestMethod() {
    return requestMethod;
  }
  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }
}
