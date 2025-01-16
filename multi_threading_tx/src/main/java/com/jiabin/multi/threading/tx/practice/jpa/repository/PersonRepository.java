package com.jiabin.multi.threading.tx.practice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.multi.threading.tx.practice.jpa.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
