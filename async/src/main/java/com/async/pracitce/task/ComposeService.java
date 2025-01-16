package com.async.pracitce.task;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ComposeService {
  
  private static final String[] COLORS = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m"} ;

  private final TaskService taskService ;
  public ComposeService(TaskService taskService) {
    this.taskService = taskService ;
  }
  
  public void task() {
    CompletableFuture<String> taskOne = this.taskService.taskOne() ;
    CompletableFuture<String> taskTwo = this.taskService.taskTwo() ;
    CompletableFuture<String> taskThree = this.taskService.taskThree() ;
    CompletableFuture.allOf(taskOne, taskTwo, taskThree).join() ;
    try {
      String one = taskOne.get() ;
      String two = taskOne.get() ;
      String three = taskOne.get() ;
      System.out.printf("%s结果获取: 任务1: %s, 任务2: %s, 任务3: %s%n", COLORS[1], one, two, three) ;
    } catch (Exception e) {
      System.err.printf("%s发生异常: %s%n", COLORS[0], e.getMessage()) ;
    }
    System.out.printf(COLORS[4]) ;
    this.taskService.otherTask() ;
  }
  
}
