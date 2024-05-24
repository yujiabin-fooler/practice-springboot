package com.jiabin.lombok.practice.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by macro on 2020/12/17.
 */
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ConstructorExample {
    @NonNull
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        //无参构造器
        ConstructorExample example1 = new ConstructorExample();
        //全部参数构造器
        ConstructorExample example2 = new ConstructorExample(1L,"test",20);
        //@NonNull注解的必须参数构造器
        ConstructorExample example3 = ConstructorExample.of(1L);
    }
}
