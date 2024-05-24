package com.jiabin.jpa.practice.dao;


import com.jiabin.jpa.practice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description dao层接口，自动实现CRUD等接口
 * @Date 2020-09-09 19:00
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {

    /**
     * 根据年龄，名字模糊查询
     * @return
     */
    Student findByNameLikeAndAge(String name,int age);
}
