package com.jiabin.kafka.producer.practice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.jiabin.kafka.producer.practice.entity.Message;
import com.jiabin.kafka.producer.practice.service.MessageService;
import com.jiabin.kafka.producer.practice.util.KafkaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiabin.yu
 * @version 1.0.0
 * @ClassName MessageServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月22日 19:54:00
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private KafkaUtils kafkaUtils;

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Override
    public void sendMsg(Object msg) {
//        for (int i = 0; i < 100; i++) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("datekey", 20210610);
//            map.put("userid", i);
//            map.put("salaryAmount", i);
//            //向kafka的big_data_topic主题推送数据
//            kafkaUtils.send(topicName, JSONObject.toJSONString(map));
//        }


        Message message = new Message();
        message.setSendUserName("xk_admin");
        message.setSendTime(DateUtil.now());
        message.setSendContent((String) msg);
        kafkaUtils.send(topicName, message);
    }
}
