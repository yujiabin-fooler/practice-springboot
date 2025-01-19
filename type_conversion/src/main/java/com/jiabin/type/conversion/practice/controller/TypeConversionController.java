package com.jiabin.type.conversion.practice.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.type.conversion.practice.domain.User;

@RestController
@RequestMapping("/conversions")
public class TypeConversionController {
  
  @InitBinder
  public void init(WebDataBinder binder) {
//    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//      @Override
//      public void setAsText(String text) throws IllegalArgumentException {
//        try {
//          Date ret = new SimpleDateFormat("yyyy-MM-dd").parse(text) ;
//          setValue(ret) ;
//        } catch (ParseException e) {
//          e.printStackTrace();
//        }
//      }
//    }) ;
    binder.registerCustomEditor(User.class, new PropertyEditorSupport() {
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasLength(text)) {
          String[] args = text.split(",") ;
          setValue(new User(args[1], Integer.valueOf(args[0]))) ;
        }
      }
    }) ;
  }
  
  /**
   * 该接口参数是由：RequestParamMethodArgumentResolver处理
   * Spring中的{@link ClassUtils#isSimpleValueType}方法中判断是否是简单类型，这其中Date是简单的类型
   * @param date
   * @return
   */
  @GetMapping("/c1")
  public Date c1(Date date) {
    return date ;
  }
  @GetMapping("/c2")
  public Date c2(@DateTimeFormat(pattern = "yyyy-MM-dd" ) Date date) {
    return date ;
  }

  /**
   * 这种接口参数（没有添加任何参数注解的）是通过ServletModelAttributeMethodProcessor进行处理的，而该处理器只会使用Spring全局的ConversionService 进行类型的转换，
   * 而我们上面的注册是将类型参数注册到当前请求的DataBinder对象中（每次请求都会创建新的对象）。
   * @param user
   * @return
   */
  @GetMapping("/c3")
  public User c3(User user) {
    return user ;
  }
  @GetMapping("/c4")
  public User c4(@RequestParam User user) {
    return user ;
  }

}
