package com.jiabin.stream.practice.custom;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/ping")
public class PingController {

  private static final String MSG_PREFIX = "data: ";
  private static final String MSG_SUFFIX = "\n\n";

  @GetMapping("")
  public ResponseEntity<ResponseBodyEmitter> subscribe() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter() ;
    try {
      emitter.send(MSG_PREFIX + "" + MSG_SUFFIX);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    new Thread(() -> {
      while (true) {
        try {
          String message = "event: ping\n";
          message += MSG_PREFIX + "{\"time\": " + System.currentTimeMillis() + "}\n" ;
          message += "id: xxxooo\n" ;
          message += "retry: 5000\n" ;
          message += MSG_SUFFIX ;
          emitter.send(message) ;
          TimeUnit.SECONDS.sleep(1) ;
        } catch (Exception e) {
          e.printStackTrace() ;
          emitter.completeWithError(e) ;
          break ;
        }
      }
    }).start() ; 
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.TEXT_EVENT_STREAM_VALUE);
    return new ResponseEntity<>(emitter, headers, 200);
  }

}