package com.jiabin.data.jpa.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import com.jiabin.data.jpa.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User>  {

  // 查询结果可能会耗尽所有内存。 获取所有数据可能会耗费大量时间。
  @Query("select u from User u")
  List<User> findAllUserList();
  
  // 查询结果可能会耗尽所有内存。 获取所有数据可能会耗费大量时间。
  @Query("select u from User u")
  Streamable<User> findAllUserStreamable();
  
  // 根据流的消费情况，分块处理（逐个或批量）。
  @Query("select u from User u")
  Stream<User> findAllUserStream();
  
  @Async
  @Query("select u from User u where u.sex = ?1 and u.age > 80")
  CompletableFuture<List<User>> findBySex(Integer sex);
  
  @Nullable
  User findByName(@Nullable String name) ;
  
//  List<User> xxxooo(Predicate<User> predicate) ;
}
