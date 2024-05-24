package com.jiabin.mongodb.practice.controller;

import com.alibaba.fastjson.JSON;
import com.jiabin.mongodb.practice.service.InsertService;
import com.jiabin.mongodb.practice.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private InsertService insertService;

    @Resource
    private QueryService QueryService;

    @RequestMapping("/insertAndQuery")
    @GetMapping
    public String insert() {
       insertService.insert();
       Object object = QueryService.findAll();
       log.info("user info " + JSON.toJSONString(object));
       return JSON.toJSONString(object);
    }

}
