package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.util.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IdGeneratorController {

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    @GetMapping("/generate-id")
    public long generateId() {
        return uniqueIdGenerator.generateUniqueId();
    }
}