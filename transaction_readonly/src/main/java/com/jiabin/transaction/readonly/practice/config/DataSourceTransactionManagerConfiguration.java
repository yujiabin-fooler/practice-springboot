package com.jiabin.transaction.readonly.practice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizer;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration(proxyBeanMethods = false)
public class DataSourceTransactionManagerConfiguration {

  //@Bean
  DataSourceTransactionManager transactionManager(Environment environment, DataSource dataSource,
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    DataSourceTransactionManager transactionManager = createTransactionManager(environment, dataSource);
    transactionManagerCustomizers
        .ifAvailable((customizers) -> customizers.customize((TransactionManager) transactionManager));
    return transactionManager;
  }

  private DataSourceTransactionManager createTransactionManager(Environment environment, DataSource dataSource) {
    return environment.getProperty("spring.dao.exceptiontranslation.enabled", Boolean.class, Boolean.TRUE)
        ? new JdbcTransactionManager(dataSource)
        : new DataSourceTransactionManager(dataSource);
  }
  
  //@Bean
  TransactionManagerCustomizer<TransactionManager> configTransactionManagerCustomizer() {
    return new TransactionManagerCustomizer<TransactionManager>() {

      @Override
      public void customize(TransactionManager transactionManager) {
        if (transactionManager instanceof DataSourceTransactionManager tm) {
          tm.setEnforceReadOnly(true) ;
        }
      }
    };
  }
}