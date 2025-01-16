package com.jiabin.extension.point.practice.error_controller;

import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class PackErrorController implements ErrorController {

  private final ObjectMapper objectMapper ;
  public PackErrorController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper ;
  }
  
  // 这里设置了响应的数据格式将是text/html; 但是返回值是ResponseEntity（会通过HttpEntityMethodProcessor处理返回值）
  // 最后会遍历HttpMessageConverter，此时应该没有任何的转换器能进行输出结果；比如StringHttpMessageConverter要求返回类型必须是String
  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String error(HttpServletRequest request) throws Exception {
    HttpStatus status = getStatus(request);
    if (status == HttpStatus.NO_CONTENT) {
      return "error";
    }
    Map<String, Object> body = Map.of("error", "发生错误") ;
    return this.objectMapper.writeValueAsString(body)  ;
  }
  
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> errorJson(HttpServletRequest request) {
    HttpStatus status = getStatus(request);
    if (status == HttpStatus.NO_CONTENT) {
      return new ResponseEntity<>(status);
    }
    Map<String, Object> body = Map.of("error", "发生错误", "code", -1) ;
    return new ResponseEntity<>(body, status);
  }
  
  protected HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    try {
      return HttpStatus.valueOf(statusCode);
    }
    catch (Exception ex) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }
  
}
