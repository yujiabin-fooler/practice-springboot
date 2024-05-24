package com.jiabin.multidatasource.jpa.practice.repository.secondary;

import com.jiabin.multidatasource.jpa.practice.entity.secondary.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentRepository
 *
 **/
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
