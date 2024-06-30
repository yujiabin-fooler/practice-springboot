package com.jiabin.redis.publish.subscribe.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
public class RedisPubSubConfig {

    @Bean
    public RedisConnectionFactory reactiveRedisConnectionFactory(RedisStandaloneConfiguration clientConfig) {

        return new LettuceConnectionFactory(clientConfig);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new PostNotificationListener(), new PatternTopic("newPostChannel"));
        return container;
    }

    @Bean
    public RedisStandaloneConfiguration clientConfig(){
        return new RedisStandaloneConfiguration();

    }

//    @Bean
//    public LettuceClientConfiguration clientConfig() {
//        return LettuceClientConfiguration.defaultConfiguration();
//    }


    @Component
    public class PostNotificationListener implements MessageListener {

        @Override
        public void onMessage(Message message, byte[] bytes) {
            // 处理接收到的帖子通知
            String postContent = Arrays.toString(message.getBody());
            System.out.println("New post notification received: " + postContent);
            // 这里可以添加逻辑，比如通知关注者
        }
    }
}