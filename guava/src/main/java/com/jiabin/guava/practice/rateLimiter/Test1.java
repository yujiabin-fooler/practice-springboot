package com.jiabin.guava.practice.rateLimiter;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class Test1 {

  private static final RateLimiter rateLimiter = RateLimiter.create(2.0) ; 
  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100)) ;
  public static void task(List<Runnable> tasks) {
    for(Runnable task : tasks) {
      // 如果没有可用的许可则会等待
      rateLimiter.acquire() ;
      executor.execute(task) ;
    }
  }
  public static void main(String[] args) {
    task(List.of(
          () -> System.out.println("任务1"),
          () -> System.out.println("任务2"),
          () -> System.out.println("任务3"),
          () -> System.out.println("任务4"),
          () -> System.out.println("任务5")
        )) ;
  }
}
