package com.async.pracitce.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {
  
  @Async
  public void task() {
    System.err.printf("%s, 异步任务执行", Thread.currentThread().getName()) ;
  }
  
  @Async
  public CompletableFuture<String> taskOne() {
    try {
      TimeUnit.MILLISECONDS.sleep(1000) ;
    } catch (InterruptedException e) {}
    System.err.printf("%s, 执行异步任务 taskOne%n", Thread.currentThread().getName()) ;
    return CompletableFuture.completedFuture("TaskOne") ;
  }
  
  @Async
  public CompletableFuture<String> taskTwo() {
    try {
      TimeUnit.MILLISECONDS.sleep(1500) ;
    } catch (InterruptedException e) {}
    System.err.printf("%s, 执行异步任务 taskTwo%n", Thread.currentThread().getName()) ;
    return CompletableFuture.completedFuture("TaskTwo") ;
  }
  
  @Async
  public CompletableFuture<String> taskThree() {
    try {
      TimeUnit.MILLISECONDS.sleep(2000) ;
    } catch (InterruptedException e) {}
    System.err.printf("%s, 执行异步任务 taskThree%n", Thread.currentThread().getName()) ;
    return CompletableFuture.completedFuture("TaskThree") ;
  }
  
  @Async
  public void otherTask() {
    try {
      TimeUnit.MILLISECONDS.sleep(2000) ;
      System.out.println(1 / 0) ;
    } catch (InterruptedException e) {}
  }
}
