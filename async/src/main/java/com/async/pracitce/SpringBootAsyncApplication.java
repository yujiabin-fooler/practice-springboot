package com.async.pracitce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.async.pracitce.task.ComposeService;
import com.async.pracitce.task.TaskService;

import jakarta.annotation.Resource;

@SpringBootApplication
@EnableAsync
public class SpringBootAsyncApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncApplication.class, args);
	}

	@Resource
	private TaskService taskService ;
	@Resource
	private ComposeService composeService ;
  @Override
  public void run(String... args) throws Exception {
    // this.taskService.task();
    this.composeService.task() ; 
  }
}
