package com.jiabin.plusar.practice.component;

import com.jiabin.plusar.practice.dto.MessageDto;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Pulsar消息生产者
 * @author jiabin.yu 2021/5/19.
 */
@Component
public class PulsarProducer {
    @Autowired
    private PulsarTemplate<MessageDto> template;

    public void send(MessageDto message){
        try {
            template.send("bootTopic",message);
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }
}
