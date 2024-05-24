package com.jiabin.multidatasource.mybatis.xml.practice.controller;

import com.jiabin.multidatasource.mybatis.xml.practice.entity.Student;
import com.jiabin.multidatasource.mybatis.xml.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * StudentResource
 *
 **/
@RestController
@RequestMapping("/api")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        Student student = studentService.getStudent(id);

        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<Void> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/students")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/paged")
    public ResponseEntity<List<Student>> getStudentsByPaged(@RequestParam Integer pageNum,
                                                            @RequestParam(required = false) Integer pageSize) {
        List<Student> students = studentService.listStudent(pageNum, pageSize);

        return ResponseEntity.ok(students);
    }

}
