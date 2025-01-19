package com.jiabin.stream.practice.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/download")
public class DownloadController {

  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(100));

  private static final String MSG_PREFIX = "data:";
  private static final String MSG_SUFFIX = "\n\n";
  
  private final ObjectMapper objectMapper ;
  public DownloadController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper ;
  }

  @GetMapping("")
  public ResponseEntity<ResponseBodyEmitter> downloadFile() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    executor.execute(() -> {
      try {
        File file = new File("D:\\devsoft\\a.txt");
        FileInputStream fis = new FileInputStream(file) ;
        emitter.send(MSG_PREFIX + "len:" + fis.available() + MSG_SUFFIX) ;
        byte[] buffer = new byte[256];
        int len ;
        while ((len = fis.read(buffer)) != -1) {
          byte[] result = new byte[len] ;
          System.arraycopy(buffer, 0, result, 0, len) ;
          emitter.send(MSG_PREFIX + this.objectMapper.writeValueAsString(Map.of("len", len, "data", Base64.getEncoder().encodeToString(result))) + MSG_SUFFIX) ;
          try {TimeUnit.SECONDS.sleep(1) ;} catch (InterruptedException e) {}
        }
        fis.close() ;
        emitter.complete();
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    });

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.TEXT_EVENT_STREAM_VALUE);
    return new ResponseEntity<>(emitter, headers, 200);
  }
}