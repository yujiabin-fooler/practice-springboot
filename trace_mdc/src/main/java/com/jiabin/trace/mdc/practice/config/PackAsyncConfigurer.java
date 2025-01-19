package com.jiabin.trace.mdc.practice.config;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.jiabin.trace.mdc.practice.filter.TraceXFilter;

@Configuration
@EnableAsync
public class PackAsyncConfigurer implements AsyncConfigurer {
  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() ;
    int core = Runtime.getRuntime().availableProcessors();
    executor.setCorePoolSize(core);
    executor.setMaxPoolSize(core);
    executor.setQueueCapacity(1000) ;
    // executor.setThreadFactory(new TraceIdThreadFactory()) ;
    executor.setTaskDecorator(new PackTraceIdTaskDecorator()) ;
    executor.setThreadNamePrefix("pack-async-") ;
    executor.initialize();
    return executor;
  }
  
  private static class PackTraceIdTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
      Map<String, String> contextMap = MDC.getCopyOfContextMap();
      return () -> {
        try {
          if (contextMap != null) {
            MDC.setContextMap(contextMap) ;
          } else {
            MDC.clear() ;
          }
          runnable.run() ;
        } finally {
          MDC.clear() ;
        }
      };
    }
  }
  
  public static class TraceIdThreadFactory implements ThreadFactory {
    private String namePrefix = "pack-async-" + "-thread-" ;
    private AtomicInteger num = new AtomicInteger(0) ;
    @Override
    public Thread newThread(Runnable r) {
      Map<String, String> contextMap = MDC.getCopyOfContextMap() ;
      Thread t = new Thread(new TraceIdRunnable(contextMap, r), namePrefix + num.incrementAndGet()) ;
      return t;
    }
  }
  public static class TraceIdRunnable implements Runnable {
    private final Map<String, String> contextMap ;
    private final Runnable delegate ;
    public TraceIdRunnable(Map<String, String> contextMap, Runnable delegate) {
      this.contextMap = contextMap ;
      this.delegate = delegate ;
    }
    @Override
    public void run() {
      try {
        MDC.setContextMap(contextMap) ;
        this.delegate.run() ; 
      } finally {
        MDC.remove(TraceXFilter.TRACE_KEY) ;
      }
    }
  }
}