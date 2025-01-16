package com.jiabin.easypdf.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.dromara.pdf.fop.handler.TemplateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/easypdf")
public class EasypdfController {

  @Value("${pack.app.pdf.configPath}")
  private String configPath ;
  @Value("${pack.app.pdf.templatePath}")
  private String templatePath ;
  
  @GetMapping("/test")
  public void genPdf(String templateName, HttpServletResponse response) throws Exception {
    Map<String,Object> data = prepareData() ;
    // 设置模板路径
    TemplateHandler.DataSource.Freemarker.setTemplatePath(templatePath) ;
    // 转换pdf
    TemplateHandler.Template.build().setConfigPath(configPath).setDataSource(
      // 构建数据源
      TemplateHandler.DataSource.Freemarker.build()
        // 设置模板名称（模板路径下的文件名称）
        .setTemplateName(templateName)
        // 设置模板数据
        .setTemplateData(data)
    ).transform(response.getOutputStream()) ;
  }
  
  private Map<String, Object> prepareData() {
    // 定义数据map
    Map<String, Object> data = new HashMap<>();
    // 定义数据list
    List<String> list = new ArrayList<>(2);
    list.add("Pack") ;
    list.add("Jeery") ;
    list.add("张三") ;
    // 设置值
    data.put("list", list);
    data.put("msg", "使用Fop模块第一个PDF文档") ;
    return data ;
  }
  
}
