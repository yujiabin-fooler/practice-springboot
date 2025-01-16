package com.jiabin.jpa.lock.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.jiabin.jpa.lock.practice.domain.Student;

import jakarta.persistence.LockModeType;

public interface StudentRepository extends JpaRepository<Student, Long> {

  //@Query(value = "select * from t_student s where s.id = ?1 for update", nativeQuery = true)
  //@Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Student> findById(Long id) ;
}
