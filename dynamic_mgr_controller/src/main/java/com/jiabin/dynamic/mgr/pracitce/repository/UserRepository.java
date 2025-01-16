package com.jiabin.dynamic.mgr.pracitce.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  public void save() {
    System.out.println("保存...") ;
  }
  
  public void remove() {
    System.out.println("删除...") ;
  }
}
