package com.jiabin.websocket.jwt.practice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "socket_jwt_users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}