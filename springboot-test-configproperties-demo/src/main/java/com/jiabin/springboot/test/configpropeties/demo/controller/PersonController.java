package com.jiabin.springboot.test.configpropeties.demo.controller;


import com.jiabin.springboot.test.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {


    @Autowired
    PersonService personService;

    @RequestMapping("/person")
    public void getPerson(){

        personService.sayHello();

    }


}
