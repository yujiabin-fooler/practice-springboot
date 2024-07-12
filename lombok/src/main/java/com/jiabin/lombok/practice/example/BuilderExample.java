package com.jiabin.lombok.practice.example;

import lombok.Builder;
import lombok.ToString;

/**
 * @author jiabin.yu 2020/12/17.
 */
@Builder
@ToString
public class BuilderExample {
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder()
                .id(1L)
                .name("test")
                .age(20)
                .build();
        System.out.println(example);
    }
}
