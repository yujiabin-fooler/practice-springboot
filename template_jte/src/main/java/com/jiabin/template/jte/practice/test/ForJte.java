package com.jiabin.template.jte.practice.test;

import java.nio.file.Path;
import java.util.List;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;

public class ForJte {

  public static void main(String[] args) {
    CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("target/classes/templates/jte")) ;
    TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html) ;
    TemplateOutput so = new StringOutput();
    templateEngine.render("for.jte", new Page("xxxooo", "这里是馍馍社交", List.of("Jack", "Pack")), so) ;
    System.err.println(so.toString()) ;
  }
  
}
