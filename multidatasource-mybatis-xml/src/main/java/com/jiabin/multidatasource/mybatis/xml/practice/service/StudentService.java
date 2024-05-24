package com.jiabin.multidatasource.mybatis.xml.practice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiabin.multidatasource.mybatis.xml.practice.entity.Student;
import com.jiabin.multidatasource.mybatis.xml.practice.mapper.primary.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StudentService
 *
 **/
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Student getStudent(Integer id) {
        Student student = studentMapper.selectById(id);

        return student;
    }

    public List<Student> listStudent(Integer pageNum, Integer pageSize) {
        // 如果 pageSize 为空，默认返回 10 条数据
        if (pageSize == null) {
            pageSize = 10;
        }
        // 分页
        Page<Student> studentPage = PageHelper.startPage(pageNum, pageSize)
                .doSelectPage(() -> studentMapper.selectAll());
        List<Student> students = studentPage.getResult();

        return students;
    }

    public void saveStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }
}
