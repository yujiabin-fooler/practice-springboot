package com.jiabin.dubbo.provider.practice;

import com.jiabin.dubbo.provider.practice.dubbo.DubboDemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${demo.service.version}")
public class DubboDemoServiceImpl implements DubboDemoService {
    @Override
    public String helloDubbo() {
        return "helloDubbo";
    }
}
