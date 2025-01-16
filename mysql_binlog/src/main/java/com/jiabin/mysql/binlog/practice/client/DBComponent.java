package com.jiabin.mysql.binlog.practice.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class DBComponent {

  public final static Map<String, List<String>> TABLE_FIELDS = new HashMap<>() ; 
  
  private final JdbcClient jdbcClient ;
  
  public DBComponent(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient ;
  }
  
  public List<String> fields(String table) {
    List<String> fields = TABLE_FIELDS.get(table) ;
    if (fields != null) {
      return fields ;
    }
    return this.jdbcClient.sql("describe " + table)
    .query(Field.class)
    .list().stream().map(Field::getField).collect(Collectors.toList()) ;
  }
  public static class Field {
    private String field ;
    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }
  }
}
