package com.jiabin.vavr.practice.domain;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
        
    private String id;
    private String login;
    private String location;

   
}