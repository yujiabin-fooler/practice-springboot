package com.jiabin.lombok.practice.example;

import lombok.AllArgsConstructor;
import lombok.With;

/**
 * @author jiabin.yu 2020/12/17.
 */
@With
@AllArgsConstructor
public class WithExample {
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        WithExample example1 = new WithExample(1L, "test", 20);
        WithExample example2 = example1.withAge(22);
        //将原对象进行clone并设置age，返回false
        System.out.println(example1.equals(example2));
    }
}
