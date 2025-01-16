package com.jiabin.dynamic.router.practice.domain;

import java.util.Date;

public class User {

	private Integer id;
	private String name;
	private String sex;
	private Integer age;
	private Date birthday;
	public User() {
  }
	public User(Integer id, String name, String sex, Integer age, Date birthday) {
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.birthday = birthday;
  }
  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
