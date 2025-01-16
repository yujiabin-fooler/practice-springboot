package com.jiabin.multi.threading.tx.practice.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;

import com.jiabin.multi.threading.tx.practice.jpa.domain.Person;

public interface PersonMapper {

  @Insert("insert into t_person(age, name) values (#{age}, #{name})")
  int save(Person person) ;
  
}
