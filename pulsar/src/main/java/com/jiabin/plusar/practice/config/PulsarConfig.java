package com.jiabin.plusar.practice.config;

import com.jiabin.plusar.practice.dto.MessageDto;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pulsar配置类
 * @author jiabin.yu 2021/5/21.
 */
@Configuration
public class PulsarConfig {
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer("bootTopic", MessageDto.class)
                .addProducer("stringTopic", String.class);
    }
}
