package com.quartz.practice.repository;

import com.quartz.practice.entity.Task;
import org.springframework.data.repository.CrudRepository;



public interface TaskRepository extends CrudRepository<Task, Long> {
}