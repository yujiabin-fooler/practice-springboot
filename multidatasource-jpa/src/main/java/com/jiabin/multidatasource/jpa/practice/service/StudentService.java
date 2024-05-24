package com.jiabin.multidatasource.jpa.practice.service;

import com.jiabin.multidatasource.jpa.practice.entity.secondary.Student;
import com.jiabin.multidatasource.jpa.practice.repository.secondary.StudentRepository;
import com.jiabin.multidatasource.jpa.practice.service.dto.StudentDTO;
import com.jiabin.multidatasource.jpa.practice.service.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StudentService
 *
 **/
@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDTO> listStudent(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        List<StudentDTO> students = page.map(e -> studentMapper.convertForStudent(e)).getContent();
        return students;
    }

    public StudentDTO saveStudent(StudentDTO dto) {
        Student student = studentMapper.convertToStudent(dto);
        studentRepository.save(student);
        return dto;
    }

    public StudentDTO updateStudent(StudentDTO dto) {
        return saveStudent(dto);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        logger.info("Delete student for id: {}", id);
    }


}
