package com.jiabin.template.jte.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiabin.template.jte.practice.test.Entry;
import com.jiabin.template.jte.practice.test.Page;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/jte")
public class TestController {

  @GetMapping("") 
  public String view(Model model, HttpServletResponse response) {
    Page page = new Page("xxxooo", "这里是馍馍社交");
    page.setEntry(new Entry("你好中国")) ;
    model.addAttribute("page", page);
    return "test";
  }
}
