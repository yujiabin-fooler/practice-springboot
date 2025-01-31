package com.jiabin.springboot.base.practice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: tabbitmq 配置信息
 * @Author jiabin.yu
 * @Date: 2019/1/21
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 消息队列名称、消息队列路由键、消费者消费队列路由键
     */
    public static final String QUEUE_NAME_DEMO = "rabbitmq_spring_boot_demo";
    public static final String QUEUE_NAME_API = "rabbitmq_api";
    public static final String QUEUE_NAME_DELAY_CART = "rabbitmq_delay_cart";

    /**
     * 交换机名称
     */
    public static final String DIRECT_EXCHANGE_NAME_DEMO = "rabbitmq_direct_exchange_demo";
    public static final String TOPIC_EXCHANGE_NAME_DEMO = "rabbitmq_topic_exchange_demo";
    public static final String TOPIC_EXCHANGE_NAME_API = "rabbitmq_topic_exchange_api";
    public static final String FANOUT_EXCHANGE_NAME_DEMO = "rabbitmq_fanout_exchange_demo";
    public static final String DIRECT_EXCHANGE_NAME_DELAY_CART = "rabbitmq_direct_exchange_delay_cart";

    /**
     * 交换机代理的路由键
     */
    public static final String DIRECT_EXCHANGE_ROUT_KEY_DEMO = "rabbitmq.spring.boot.demo";
    public static final String TOPIC_EXCHANGE_ROUT_KEY_DEMO = "rabbitmq.spring.boot.#";
    public static final String TOPIC_EXCHANGE_ROUT_KEY_API = "rabbitmq.api.#";
    public static final String DIRECT_EXCHANGE_ROUT_KEY_DELAY_CART = "rabbitmq.spring.boot.cart";

    /**
     * 生产者发送路由键
     */
    public static final String QUEUE_SENDER_ROUTING_KEY_DEMO = "rabbitmq.spring.boot.demo";
    public static final String QUEUE_SENDER_ROUTING_KEY_DEMO_2 = "rabbitmq.spring.boot.demo.2";
    public static final String QUEUE_SENDER_ROUTING_KEY_API_USER = "rabbitmq.api.user";
    public static final String QUEUE_SENDER_ROUTING_KEY_DELAY_CART = "rabbitmq.spring.boot.cart";

    /**
     * 延时时长(单位:毫秒)
     */
    public static final int QUEUE_DELAY_TIME_CART = 30000;


    /**
     * 定义队列(demo)
     *
     * @return
     */
    @Bean("queueDemo")
    public Queue queueDemo(){
        return new Queue(QUEUE_NAME_DEMO);
    }

    /**
     * 定义队列(api)
     *
     * @return
     */
    @Bean("queueApi")
    public Queue queueApi(){
        return new Queue(QUEUE_NAME_API);
    }

    /**
     * 定义延时队列(cart)
     *
     * @return
     */
    @Bean("queueDelayCart")
    public Queue queueDelayCart() {
        return new Queue(QUEUE_NAME_DELAY_CART, true, false, true);
    }

    /**
     * 定义直连交换机(Demo)
     *
     * @return
     */
    @Bean("directExchangeDemo")
    public DirectExchange directExchangeDemo() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME_DEMO, false, true);
    }

    /**
     * 定义主题交换机(demo)
     *
     * @return
     */
    @Bean("topicExchangeDemo")
    public TopicExchange topicExchangeDemo(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME_DEMO, false, true);
    }

    /**
     * 定义主题交换机(api)
     *
     * @return
     */
    @Bean("topicExchangeApi")
    public TopicExchange topicExchangeApi(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME_API, false, true);
    }

    /**
     * 定义广播交换机(demo)
     *
     * @return
     */
    @Bean("fanoutExchangeDemo")
    public FanoutExchange fanoutExchangeDemo() {
       return new FanoutExchange(FANOUT_EXCHANGE_NAME_DEMO, false, true);
    }

    /**
     * 定义延时交换机(cart)
     * @return
     */
    @Bean("delayExchangeCart")
    public CustomExchange delayExchangeCart() {
        Map<String, Object> args = new HashMap<>(16);
        args.put("x-delayed-type", "direct");
        CustomExchange exchange = new CustomExchange(DIRECT_EXCHANGE_NAME_DELAY_CART,"x-delayed-message",
                false, true, args);
        return exchange;
    }

    /**
     * 绑定直连交换机与队列(direct demo)
     * @param queue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirectExchangeDemo(@Qualifier("queueDemo") Queue queue,
                                             @Qualifier("directExchangeDemo") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(DIRECT_EXCHANGE_ROUT_KEY_DEMO);
    }

    /**
     * 绑定主题交换机与队列(demo)
     *
     * @param queue
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeDemo(@Qualifier("queueDemo") Queue queue,
                                            @Qualifier("topicExchangeDemo") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(TOPIC_EXCHANGE_ROUT_KEY_DEMO);
    }

    /**
     * 绑定主题交换机与队列(api)
     *
     * @param queueApi
     * @param topicExchangeApi
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeApi(@Qualifier("queueApi") Queue queueApi,
                                           @Qualifier("topicExchangeApi") TopicExchange topicExchangeApi) {
        return BindingBuilder.bind(queueApi).to(topicExchangeApi).with(TOPIC_EXCHANGE_ROUT_KEY_API);
    }

    /**
     * 绑定广播交换机与队列(demo)
     *
     * @param queue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bingingFanoutExchangeDemo(@Qualifier("queueDemo") Queue queue,
                                             @Qualifier("fanoutExchangeDemo") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /**
     * 绑定广播交换机与队列(api)
     *
     * @param queue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bingingFanoutExchangeApi(@Qualifier("queueApi") Queue queue,
                                             @Qualifier("fanoutExchangeDemo") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /**
     * 绑定延时交换机与队列(cart)
     *
     * @param queue
     * @param customExchange
     * @return
     */
    @Bean
    public Binding bindingDirectExchangeDelayCart(@Qualifier("queueDelayCart") Queue queue,
                                                  @Qualifier("delayExchangeCart") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DIRECT_EXCHANGE_ROUT_KEY_DELAY_CART).noargs();
    }



}
