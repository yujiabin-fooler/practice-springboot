package com.jiabin.template.jte.practice.test;

import java.util.ArrayList;
import java.util.List;

public class Page {

  private String title ;
  private String description ;
  public List<String> entries = new ArrayList<>() ;
  private Entry entry ;
  public Page() { 
  }
  public Page(String title, String description) {
    this.title = title;
    this.description = description;
  }
  public Page(String title, String description, List<String> entries) {
    this.title = title;
    this.description = description;
    this.entries = entries ;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public List<String> getEntries() {
    return entries;
  }
  public void setEntries(List<String> entries) {
    this.entries = entries;
  }
  public Entry getEntry() {
    return entry;
  }
  public void setEntry(Entry entry) {
    this.entry = entry;
  }
}
