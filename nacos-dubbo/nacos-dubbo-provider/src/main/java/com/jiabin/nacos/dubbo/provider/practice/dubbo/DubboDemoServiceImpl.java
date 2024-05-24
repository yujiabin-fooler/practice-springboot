package com.jiabin.nacos.dubbo.provider.practice.dubbo;


import com.jiabin.nacos.dubbo.provider.practice.DubboDemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${demo.service.version}")
public class DubboDemoServiceImpl implements DubboDemoService {
    @Override
    public String helloDubbo() {
        return "helloDubbo";
    }
}
