package com.quartz.practice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String name;//任务名

    @Column(name = "`group`")
    private String group;//分组
    
    private String status; // 任务状态

    private String cronExpression;//定时表达式

    private String className;//要执行类名

}
