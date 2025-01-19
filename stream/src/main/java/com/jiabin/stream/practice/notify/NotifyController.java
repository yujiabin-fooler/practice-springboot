package com.jiabin.stream.practice.notify;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/notify")
public class NotifyController {

  private final CopyOnWriteArrayList<ResponseBodyEmitter> emitters = new CopyOnWriteArrayList<>() ;
  
  private static final String MSG_PREFIX = "data:";
  private static final String MSG_SUFFIX = "\n\n";

  @GetMapping("")
  public ResponseEntity<ResponseBodyEmitter> subscribe() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter() ;
    emitters.add(emitter) ;
    try {
      emitter.send(MSG_PREFIX + "" + MSG_SUFFIX);
    } catch (IOException e) {
      e.printStackTrace();
    }
    emitter.onCompletion(() -> emitters.remove(emitter)) ;
    emitter.onError(t -> {
      System.err.println("关闭连接: " + t) ;
      emitters.remove(emitter) ;
    }) ;
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.TEXT_EVENT_STREAM_VALUE);
    return new ResponseEntity<>(emitter, headers, 200);
  }

  @GetMapping("/publish")
  public void publish(String message) {
    for (ResponseBodyEmitter emitter : emitters) {
      try {
        emitter.send(MSG_PREFIX + message + MSG_SUFFIX);
      } catch (Exception e) {
        emitters.remove(emitter);
      }
    }
  }
}