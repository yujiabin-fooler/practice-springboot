package com.jiabin.jetcache.practice.model.entity;

import com.jiabin.jetcache.practice.model.param.student.StudentReceiveParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 学生植树
 * @Author jiabin.yu
 * @Date: 2021/8/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeEntity implements Serializable {

    private static final long serialVersionUID = 3727053267859322959L;

    /**
     * 数目品种
     */
    private String treeType;
    /**
     * 植树时间
     */
    private Integer plantDate;
    /**
     * 学生信息
     */
    private StudentReceiveParam student;
    /**
     * 选修课
     */
    private ElectiveCourseEntity electiveCourse;
    /**
     * 教师列表
     */
    private List<TeacherEntity> teachers;

}
