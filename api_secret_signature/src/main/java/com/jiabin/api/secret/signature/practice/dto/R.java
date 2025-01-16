package com.jiabin.api.secret.signature.practice.dto;

public class R {

  private String traceId ;
  private Integer code ;
  private Object data ;
  private String message ;
  
  public R(Integer code, Object data, String message) {
    this.code = code ;
    this.data = data ;
    this.message = message ;
  }
  
  public R(String traceId, Integer code, Object data, String message) {
    this.traceId = traceId ;
    this.code = code ;
    this.data = data ;
    this.message = message ;
  }
  
  public static R success(Object data, String traceId) {
    return new R(traceId, 200, data, "success") ;
  }
  public static R success(Object data) {
    return new R(200, data, "success") ;
  }
  public static R failure(String message) {
    return new R(500, null, message) ;
  }
  public static R failure(String traceId, String message) {
    return new R(traceId, 500, null, message) ;
  }
  
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public Object getData() {
    return data;
  }
  public void setData(Object data) {
    this.data = data;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }
  
}
