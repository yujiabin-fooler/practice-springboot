package com.jiabin.cross.domain.practice.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * HelloController
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/5/24
 */
@RestController
public class UserController {

    @CrossOrigin(origins = "http://127.0.0.1:8848", maxAge = 1800)
    @GetMapping(value = "/queryAll")
    public String queryAll(){
        return "hello world";
    }
}
