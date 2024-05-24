package com.jiabin.content.negotiation.practice.repository;

import com.jiabin.content.negotiation.practice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}