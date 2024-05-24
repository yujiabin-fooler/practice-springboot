package com.jiabin.kafka.producer.practice.controller;

import com.jiabin.kafka.producer.practice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jiabin.yu
 * @version 1.0.0
 * @ClassName MessageController.java
 * @Description TODO
 * @createTime 2022年05月22日 19:53:00
 */
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(path = "/send/{content}")
    public void sendMessage(@PathVariable String content) {
        messageService.sendMsg(content);
    }

}
