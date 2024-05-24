package com.jiabin.aop.practice.controller;

import com.jiabin.aop.practice.annotation.Log;
import com.jiabin.aop.practice.domain.Person;
import com.jiabin.aop.practice.enums.BusinessType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 接口类，生成swagger接口文档
 * @Date 2020-08-28 11:29
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @GetMapping("/{name}")
    @Log(title = "system",businessType = BusinessType.OTHER)
    public Person getPerson(@PathVariable("name") String name, @RequestParam int age){
        return new Person(name,age);
    }

    @PostMapping("add")
    @Log(title = "system",businessType = BusinessType.INSERT)
    public int addPerson(@RequestBody Person person){
        if(StringUtils.isEmpty(person)){
            return -1;
        }
        return 1;
    }

    @PutMapping("update")
    @Log(title = "system",businessType = BusinessType.UPDATE)
    public int updatePerson(@RequestBody Person person){
        if(StringUtils.isEmpty(person)){
            return -1;
        }
        return 1;
    }

    @DeleteMapping("/{name}")
    @Log(title = "system",businessType = BusinessType.DELETE)
    public int deletePerson(@PathVariable(name = "name") String name){
        if(StringUtils.isEmpty(name)){
            return -1;
        }
        return 1;
    }
}