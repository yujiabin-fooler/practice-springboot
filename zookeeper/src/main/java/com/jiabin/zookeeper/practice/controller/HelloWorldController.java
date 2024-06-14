package com.jiabin.zookeeper.practice.controller;

import com.jiabin.zookeeper.practice.api.ZkApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController {
    @Autowired
    private ZkApi zkApi;

    @GetMapping(value = "createNode")
    public boolean createNode(String path, String data) {
        log.debug("ZookeeperController create node {},{}", path, data);
        return zkApi.createNode(path, data);
    }
}