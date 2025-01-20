package com.jiabin.trace.mdc.practice.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MDCTest {

  private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));


  public static void main(String[] args) {

    Logger logger = LoggerFactory.getLogger(MDCTest.class) ;
    
    MDC.put("traceXId", "xxxooo") ;
    
    logger.info("执行业务操作") ;
    logger.info("业务执行完成") ;
    
    MDC.remove("traceXId") ;
    
    try {
      Map<String, String> contextMap = MDC.getCopyOfContextMap();
      executor.execute(() -> {
        MDC.setContextMap(contextMap) ;
        logger.info("执行远程操作操作...") ;
      }) ;
    }catch(RuntimeException e){
      System.out.println(e.getMessage());
    }
  }
  
}
