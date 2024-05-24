package com.jiabin.rocketmq.spring.practice.service;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 生产者
 * 原始生产者api调用方式。 由生产者产生消息，发送并存储到broker ，
 *
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建一个生产者，指定生产者组为myPracticeProducer
        DefaultMQProducer producer = new DefaultMQProducer("myPracticeProducer");

        // 指定NameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 第一次发送可能会超时，我设置的比较大
        producer.setSendMsgTimeout(60000);

        // 启动生产者
        producer.start();

        //创建一条消息
        // topic为 myPracticeTopic
        // 消息内容为 这是一条消息，用于测试rocketmq 发送消息
        // tags 为 TagA
        Message msg = new Message("myPracticeTopic", "TagA", "这是一条消息，用于测试rocketmq 发送消息 ".getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 发送消息并得到消息的发送结果，然后打印
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);

        // 关闭生产者
        producer.shutdown();
    }

}