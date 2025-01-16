package com.jiabin.big.transaction.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.big.transaction.practice.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
