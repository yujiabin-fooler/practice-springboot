package com.jiabin.practice.dubbo.servide.register.provider;

import com.jiabin.dubbo.service.register.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 生产者
 *
 * @author jiabin.yu
 * @date 2024/10/5 22:46
 */
@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
