package com.jiabin.disruptor.practice.event;

import com.jiabin.disruptor.practice.model.MessageModel;
import com.lmax.disruptor.EventFactory;

public class HelloEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
