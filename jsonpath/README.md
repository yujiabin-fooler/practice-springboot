使用Spring Boot + Hutool + FastJson 实现接口调用及 JsonPath 解析属性值

在使用Spring Boot和Hutool调用JSONPlaceholder API并通过类似XPath的方式查询`title`或`body`的值时，可以使用JSONPath来实现。JSONPath是一种用于在JSON文档中定位和提取数据的语言，类似于XPath用于XML文档的功能。在Java中，Hutool提供了对JSONPath的支持。

首先，确保项目中引入了Spring Boot和Hutool的依赖。

```xml
<!-- Spring Boot Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Hutool -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.7.4</version> 
</dependency>

<!-- FastJSON -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.78</version> 
</dependency>
```

接下来，可以使用如下的代码示例：

`JSONPathUtil`的实现：

```java
package com.icoderoad.example.jsonpath.util;

import java.util.List;

import com.alibaba.fastjson.JSONPath;

public class JSONPathUtil {

    public static <T> T getByPath(String json, String path) {
        Object result = JSONPath.read(json, path);
        if (result instanceof List) {
            List<?> listResult = (List<?>) result;
            if (!listResult.isEmpty()) {
                return (T) listResult.get(0);
            }
        }
        return (T) result;
    }

    public static void main(String[] args) {
        String json = "[{\"title\":\"Title 1\",\"body\":\"Body 1\"},{\"title\":\"Title 2\",\"body\":\"Body 2\"}]";
        String title = getByPath(json, "$..title");
        String body = getByPath(json, "$..body");

        System.out.println("Title 值: " + title);
        System.out.println("Body 值: " + body);
    }
}
```

在`JSONPathUtil`类中，我们使用了FastJSON库中的`JSONPath`类来实现JSONPath的查询，然后通过`getByPath`方法返回查询结果。这个方法支持返回单个值或列表中的第一个值。

```java
package com.icoderoad.example.jsonpath.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.icoderoad.example.jsonpath.util.JSONPathUtil;

import cn.hutool.http.HttpRequest;
import lombok.Data;

@RestController
@RequestMapping("/api/posts")
public class JsonPathRestController {

    @GetMapping
    public String getPosts() {
        // 发起HTTP请求获取JSON数据
        String json = HttpRequest.get("http://jsonplaceholder.typicode.com/posts").execute().body();

        // 使用JSONPath查询title和body的值
        String title = JSONPathUtil.getByPath(json, "$..title");
        String body = JSONPathUtil.getByPath(json, "$..body");

        // 构建返回结果
        ResultData resultData = new ResultData(title, body);

        // 将Java对象转换为JSON字符串
        return JSON.toJSONString(resultData);
    }

    // 用于封装返回结果的简单Java对象
    @Data
    private static class ResultData {
        private String title;
        private String body;

        public ResultData(String title, String body) {
            this.title = title;
            this.body = body;
        }

       
    }
}
```

在这个例子中，我们创建了一个名为 `JsonPathRestController` 的类，使用 `@RestController` 注解标记为 RESTful 接口，通过 `@RequestMapping` 指定了请求路径。在 `getPosts` 方法中，我们发起 HTTP 请求获取 JSON 数据，并使用 `JSONPathUtil` 类进行 JSONPath 查询。最后，我们将查询结果构建成JSON字符串，并作为接口的返回值。

请确保在项目中引入了Hutool和FastJSON的相关依赖。这个示例展示了如何使用Spring Boot和Hutool发起HTTP请求，并通过JSONPath查询JSON数据中的特定字段的值。