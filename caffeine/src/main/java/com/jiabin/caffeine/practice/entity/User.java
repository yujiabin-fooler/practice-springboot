package com.jiabin.caffeine.practice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="caffeine_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private int age;

}