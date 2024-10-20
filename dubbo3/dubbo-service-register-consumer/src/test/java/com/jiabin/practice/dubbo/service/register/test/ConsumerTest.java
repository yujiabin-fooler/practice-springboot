package com.jiabin.practice.dubbo.service.register.test;

import com.jiabin.dubbo.service.register.api.DemoService;
import com.jiabin.practice.dubbo.service.register.consumer.ConsumerApplication;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 消费者测试
 *
 * @author jiabin.yu
 * @date 2024/10/5 22:46
 */
@SpringBootTest(classes = ConsumerApplication.class)
@RunWith(SpringRunner.class)
public class ConsumerTest {

    @DubboReference(
    )
    private DemoService demoService;

    @Test
    public void testSayHello() {
        String result = demoService.sayHello("三友的java日记");
        System.out.println(result);
    }

}
