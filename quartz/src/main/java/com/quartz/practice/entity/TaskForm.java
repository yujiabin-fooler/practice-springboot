package com.quartz.practice.entity;

public class TaskForm {
    private String name;
    private String group;
    private String ClassName;
    private String cronExpression;

    public TaskForm() {
        // 默认构造函数
    }

    public TaskForm(String name, String group, String cronExpression) {
        this.name = name;
        this.group = group;
        this.cronExpression = cronExpression;
    }
    
    public TaskForm(String name, String group, String cronExpression, String className) {
    	this.name = name;
    	this.group = group;
    	this.cronExpression = cronExpression;
    	this.ClassName = className;		
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                '}';
    }
}