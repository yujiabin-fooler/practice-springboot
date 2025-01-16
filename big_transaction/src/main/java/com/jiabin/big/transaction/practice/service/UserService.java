package com.jiabin.big.transaction.practice.service;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class UserService {

  private final DataSource dataSource ;
  public UserService(DataSource dataSource) {
    this.dataSource = dataSource ;
  }
  
  @Transactional
  public void save() {
    ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource) ;
    System.err.println("UserService: " + conHolder.getConnection().hashCode()) ;
    try {
      TimeUnit.SECONDS.sleep(10) ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
