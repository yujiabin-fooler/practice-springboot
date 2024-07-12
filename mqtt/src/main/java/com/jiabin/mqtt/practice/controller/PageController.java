package com.jiabin.mqtt.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiabin.yu 2020/9/16.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
