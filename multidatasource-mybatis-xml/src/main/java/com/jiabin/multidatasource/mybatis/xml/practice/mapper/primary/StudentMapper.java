package com.jiabin.multidatasource.mybatis.xml.practice.mapper.primary;

import com.jiabin.multidatasource.mybatis.xml.practice.entity.Student;

import java.util.List;

/**
 * StudentMapper
 * <p>
 * 注意：方法名（如：selectById）要与 StudentMapper.xml文件中的 id 对应
 *
 * @author star
 **/
public interface StudentMapper {

    Student selectById(Integer id);

    List<Student> selectAll();

    void updateStudent(Student student);

    void insertStudent(Student student);

    void deleteStudent(Integer id);

}
