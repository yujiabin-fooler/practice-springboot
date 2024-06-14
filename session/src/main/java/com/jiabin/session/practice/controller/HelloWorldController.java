package com.jiabin.session.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public Map<String, Object> showHelloWorld(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "HelloWorld");
        return map;
    }
    @RequestMapping("/get/{name}")
    public String getSesseion(HttpServletRequest request, @PathVariable("name") String name){
        HttpSession session = request.getSession();
        String value = (String)session.getAttribute(name);
        return "sessionId:"+session.getId()+" value:"+value;
    }
    @RequestMapping("/add/{name}/{value}")
    public String addSession(HttpServletRequest request,@PathVariable("name") String name,@PathVariable("value") String value){
        HttpSession session = request.getSession();
        session.setAttribute(name,value);
        return "sessionId:"+session.getId()+" name:"+name;
    }

}