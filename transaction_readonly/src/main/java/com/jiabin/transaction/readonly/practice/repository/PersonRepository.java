package com.jiabin.transaction.readonly.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jiabin.transaction.readonly.practice.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
  
  @Query("select p from Person p where p.id = ?1")
  Person queryById(Integer id) ;
}
