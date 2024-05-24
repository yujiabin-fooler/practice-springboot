package com.jiabin.jdbctemplate.practice.dao;

import com.jiabin.jdbctemplate.practice.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 公众号：Java旅途
 *  @Description 数据层操作类
 * @Date 2020-08-31 11:24
 */
@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student getStudent(int status){
        String sql = "select * from student where status = ? limit 1";
        return jdbcTemplate.queryForObject(sql,new Object[]{status},new BeanPropertyRowMapper<>(Student.class));
    }

    public List<Student> studentBeanList(int status){
        String sql = "select * from student where status = ?";
        return  jdbcTemplate.query(sql,new Object[]{status},new BeanPropertyRowMapper<>(Student.class));
    }

    public int deleteStudent(int status){
        String sql = "delete from student where status = 0";
        return jdbcTemplate.update(sql);
    }

    public int updateStudent(int studentId,String name){
        String sql = "update student set name = ? where student_id = ?";
        return jdbcTemplate.update(sql,new Object[]{name,studentId});
    }

    public int addStudent(){
        String sql = "insert into student(student_id,age,name,status) values(?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{30,18,"Java旅途",0});
    }

    public int batchAddStudent(){

        // 构造list集合
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(31, 31, "小周", 1, new Date(), 1);
        Student student1 = new Student(32, 32, "小周周", 1, new Date(), 1);
        studentList.add(student);
        studentList.add(student1);
        String sql = "insert into student values(?,?,?,?,?,?)";

        int[] ints = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Student student = studentList.get(i);
                ps.setInt(1, student.getStudentId());
                ps.setInt(2, student.getAge());
                ps.setString(3, student.getName());
                ps.setInt(4, student.getSex());
                ps.setDate(5,new java.sql.Date(System.currentTimeMillis()));
                ps.setInt(6, student.getStatus());
            }

            @Override
            public int getBatchSize() {
                return studentList.size();
            }
        });
        return ints.length;
    }

}