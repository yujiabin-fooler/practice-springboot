package com.jiabin.stream.practice.log;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/logs")
public class LogController {
  
  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100)) ;

  private static final String  MSG_PREFIX = "data:" ;
  private static final String MSG_SUFFIX = "\n\n" ;
  
  @GetMapping(value = "")
  public ResponseEntity<ResponseBodyEmitter> streamLogs() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    
    executor.execute(() -> {
      try {
        // 复杂计算业务
        for (int i = 0; i < 10; i++) {
          emitter.send(MSG_PREFIX + Map.of("code", 0, "message", "数据 - " + i + ", 计算完成") + MSG_SUFFIX) ;
          // 模拟耗时
          TimeUnit.SECONDS.sleep(1) ;
        }
        System.err.println(System.currentTimeMillis() + ", over...") ;
        // 完成请求处理
        emitter.complete() ;
      } catch (Exception e) {
        e.printStackTrace() ;
        emitter.completeWithError(e) ;
      }
    }) ;
    HttpHeaders headers = new HttpHeaders() ;
    headers.add("Content-Type", MediaType.TEXT_EVENT_STREAM_VALUE) ;
    return new ResponseEntity<>(emitter, headers, 200) ;
  }
}