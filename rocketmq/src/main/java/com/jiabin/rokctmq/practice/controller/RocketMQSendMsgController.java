package com.jiabin.rokctmq.practice.controller;

import com.jiabin.rokctmq.practice.constants.MQConfig;
import com.jiabin.rokctmq.practice.domain.RocketMQMessage;
import com.jiabin.rokctmq.practice.service.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("practice/rocketMQMessage/")
public class RocketMQSendMsgController {



    @Resource
    RocketMQService rocketMQService;

    private static  final List<Map<String,Object>>  usersList = new ArrayList<>();

    static {

         Map<String,Object> userMap = new HashMap<>();
         userMap.put("name","xiaoming");
         userMap.put("age",12);
         userMap.put("address","上海浦东");
         userMap.put("phone","13971314134");
         usersList.add(userMap);
         Map<String,Object> user2Map = new HashMap<>();
         userMap.put("name","xiaomin");
         userMap.put("age",18);
         userMap.put("address","湖北红安");
         userMap.put("phone","13971314520");
         usersList.add(user2Map);
    }


    private RocketMQMessage<Map<String,Object>> buildMessage() {
        RocketMQMessage<Map<String,Object>> message = new RocketMQMessage<>();
        message.setTaskId(UUID.randomUUID().toString());
        message.setTopic(MQConfig.EVENT_TOPIC);
        message.setHashKey(UUID.randomUUID().toString());
        message.setMessages(usersList);
        message.setMessage(usersList.get(0));
        return message;
    }




    /**
     * 测试发送单向消息
     *
     */
    @RequestMapping("testSendOneWay")
    @GetMapping
    public void testSendOneWay(){

        log.info("MQListener 发送消息 ----- ");
        rocketMQService.sendOneWay(buildMessage());
        log.info("MQListener 发送成功 ----- ");
    }

    /**
     * 测试发送批量单向消息
     *
     */
    @RequestMapping("tesSendOneWayBatch")
    @GetMapping
    public void tesSendOneWayBatch(){

        log.info("MQListener 发送消息 ----- ");
        rocketMQService.sendOneWay(buildMessage(),true);
        log.info("MQListener 发送成功 ----- ");

    }

    /**
     * 测试发送同步消息
     *
     */
    @RequestMapping("testSyncSend")
    @GetMapping
    public void testSyncSend(){
        log.info("MQListener 发送消息 ----- ");
        rocketMQService.syncSend(buildMessage());
        log.info("MQListener 发送成功 ----- ");
    }

    /**
     * 测试发送异步消息
     *
     */
    @RequestMapping("asyncSend")
    @GetMapping
    public void asyncSend(){

        log.info("MQListener 发送消息 ----- ");
        rocketMQService.asyncSend(buildMessage());
        log.info("MQListener 发送成功 ----- ");
    }

    /**
     * 测试发送批量异步消息
     *
     */
    @RequestMapping("testAsyncSendBatch")
    @GetMapping
    public void testAsyncSendBatch(){

        log.info("MQListener 发送消息 ----- ");
        rocketMQService.asyncSendBatch(buildMessage());
        log.info("MQListener 发送成功 ----- ");
    }

    /**
     * 测试发送批量同步步消息
     *
     */
    @RequestMapping("testSyncSendBatch")
    @GetMapping
    public void testSyncSendBatch(){

        log.info("MQListener 发送消息 ----- ");
        rocketMQService.syncSendBatch(buildMessage());
        log.info("MQListener 发送成功 ----- ");
    }




}
