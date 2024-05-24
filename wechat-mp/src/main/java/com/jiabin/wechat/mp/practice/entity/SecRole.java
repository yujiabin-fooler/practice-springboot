package com.jiabin.wechat.mp.practice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sec_role")
public class SecRole {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
}