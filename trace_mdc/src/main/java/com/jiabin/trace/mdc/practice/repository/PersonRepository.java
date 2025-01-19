package com.jiabin.trace.mdc.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.trace.mdc.practice.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
