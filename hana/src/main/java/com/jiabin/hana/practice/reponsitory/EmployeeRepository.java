package com.jiabin.hana.practice.reponsitory;

import com.jiabin.hana.practice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
