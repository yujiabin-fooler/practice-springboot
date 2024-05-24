package com.jiabin.wechat.mp.practice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sec_user")
public class SecUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sec_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<SecRole> roles = new HashSet<>();
    
}