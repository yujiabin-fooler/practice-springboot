package com.jiabin.template.jte.practice.test;

import java.nio.file.Path;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

public class TestJte {

  public static void main(String[] args) {
    CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("target/classes/templates/jte")) ;
    TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html) ;
    TemplateOutput so = new StringOutput();
    Page page = new Page("xxxooo", "这里是馍馍社交");
    page.setEntry(new Entry("你好中国")) ;
    templateEngine.render("test.jte", page, so) ;
    System.err.println(so.toString()) ;
  }
  
}
