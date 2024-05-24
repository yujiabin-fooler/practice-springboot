package com.jiabin.aop.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description person实体类
 * @Date 2020-08-28 11:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;

    private Integer age;
}
