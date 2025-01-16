package com.jiabin.jpa.lock.practice.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "t_student")
@NamedQuery(name="lockStudent", query="SELECT s FROM Student s WHERE s.id = :id", lockMode = LockModeType.PESSIMISTIC_READ)
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id ;
  private String name ;
  private String sno ;
  @Version
  private Integer version ;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "sid")
  @Fetch(FetchMode.SELECT)
  private List<Course> courses = new ArrayList<>() ;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSno() {
    return sno;
  }
  public void setSno(String sno) {
    this.sno = sno;
  }
  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", sno=" + sno + ", version=" + version + ", courses=" + courses
        + "]";
  }
  public Integer getVersion() {
    return version;
  }
  public void setVersion(Integer version) {
    this.version = version;
  }
  public List<Course> getCourses() {
    return courses;
  }
  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
