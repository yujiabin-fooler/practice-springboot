package com.jiabin.practice.itextdemo.controller;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import com.jiabin.practice.itextdemo.utils.HtmlToPdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;


@RestController
public class HelloController {

    @Autowired
    TemplateEngine templateEngine;

    @GetMapping("/getBookInfo")
    public void getBookInfo(HttpServletResponse response) throws IOException, DocumentException {
        Context ctx = new Context();
        ctx.setVariable("title","<深入浅出 Spring Security>图书详情");
        ctx.setVariable("name","<深入浅出 Spring Security>");
        ctx.setVariable("price","99.00");
        ctx.setVariable("author","江南一点雨");
        String bookInfo = templateEngine.process("book_info", ctx);
        response.setContentType("application/pdf;charset=utf-8");
        HtmlToPdfUtils.convertToPdf(bookInfo, response.getOutputStream());
    }
}
