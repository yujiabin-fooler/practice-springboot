package com.jiabin.easypdf.practice.fop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dromara.pdf.fop.handler.TemplateHandler;

public class Test {

  public static void main(String[] args) {
    // 定义fop配置文件路径
    String configPath = "E:\\pdf\\fop.xconf";
    // 定义xsl-fo模板路径（目录）
    String templatePath = "E:\\pdf\\template";
    // 定义pdf输出路径
    String outputPath = "E:\\pdf\\Freemarker.pdf";
    // 设置模板路径
    TemplateHandler.DataSource.Freemarker.setTemplatePath(templatePath);
    // 定义数据map
    Map<String, Object> data = new HashMap<>();
    // 定义数据list
    List<String> list = new ArrayList<>(2);
    list.add("Pack") ;
    list.add("Jeery") ;
    list.add("张三") ;
    // 设置值
    data.put("list", list);
    data.put("msg", "First easypdf fop");
    // 转换pdf
    TemplateHandler.Template.build().setConfigPath(configPath).setDataSource(
            // 构建数据源
            TemplateHandler.DataSource.Freemarker.build()
                // 设置模板名称（模板路径下的文件名称）
                .setTemplateName("test.fo")
                // 设置模板数据
                .setTemplateData(data)
    ).transform(outputPath);

  }
  
}
