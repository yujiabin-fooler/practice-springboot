package com.jiabin.sensitive.word.check.practice.domain;

import java.util.Date;

import com.jiabin.sensitive.word.check.practice.annotation.Sensitive;

public class Post {

  @Sensitive(message = "违规的标题")
  private String title ;
  @Sensitive(message = "帖子内容存在违规信息")
  private String content ;
  @Sensitive(message = "违规的发表人")
  private String author ;
  private String email ;
  private Integer words ;
  private Date time ;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Integer getWords() {
    return words;
  }
  public void setWords(Integer words) {
    this.words = words;
  }
  public Date getTime() {
    return time;
  }
  public void setTime(Date time) {
    this.time = time;
  }
  
}
