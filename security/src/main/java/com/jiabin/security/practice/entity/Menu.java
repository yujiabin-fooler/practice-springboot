package com.jiabin.security.practice.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
 
    private Integer id;
    private String pattern;
    private List<Role> roles;
 
}