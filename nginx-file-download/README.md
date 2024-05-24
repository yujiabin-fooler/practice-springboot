使用 nginx 的 secure_link 模块与SpringBoot 框架结合实现图片及视频防盗链功能

为了提供一个完整的项目示例，包括Spring Boot应用、Thymeleaf视图、Nginx配置等，我会提供一个基本的项目结构示例，并演示如何配置和编写相关代码。请注意，以下示例是一个简单的起始点，您可以根据您的需求进行进一步扩展和优化。

**创建Spring Boot应用：**

首先，创建一个Spring Boot应用。可以使用Spring Initializr或其他方式创建项目。

确保您的项目中包括`spring-web`和`spring-boot-starter-thymeleaf`依赖。

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
  	<dependency>
		    <groupId>commons-codec</groupId>
		    <artifactId>commons-codec</artifactId>
		    <version>1.16.0</version>
		</dependency>
</dependencies>
```

**创建一个Java实体类来FileObject.java：**

```java
package com.jiabin.nginx.file.download.practice.entity;

import lombok.Data;

@Data
public class FileObject {
    private String fileName;
    private String md5;
    private String expires;
    private String downloadUrl;
}
```

**创建控制类对象 FileController.java** 

然后，在`FileController`中，添加 index`方法，以返回一个包含`FileObject`对象的列表：

```java
package com.jiabin.nginx.file.download.practice.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiabin.nginx.file.download.practice.entity.FileObject;

@Controller
public class FileController {
  
    @Value("${upload_dir}")
    private String uploadDir;
  
  	@Value("${secure_link_secret}")
    private String secureLinkSecret;
  
  	@Value("${download_host}")
    private String downloadHost;

    @GetMapping("/")
    public String index(Model model) {
        List<FileObject> fileList = new ArrayList<>();
        File uploadDirectory = new File(uploadDir);
        
        if (uploadDirectory.exists() && uploadDirectory.isDirectory()) {
            File[] files = uploadDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    FileObject fileObject = new FileObject();
                    fileObject.setFileName(file.getName());
                  	fileObject.setDownloadUrl( downloadHost +getBaseName(file.getName()));
                  	String expires = generateExpires();
                    fileObject.setMd5(generateMd5(file.getName(), expires)); // 生成MD5串的方法
                    fileObject.setExpires(expires); // 生成expires的方法
                    fileList.add(fileObject);
                }
            }
        }

        model.addAttribute("fileList", fileList);
        return "index";
    }
 
   @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        // 处理文件上传
        Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        file.transferTo(filePath.toFile());

        // 重定向回首页
        redirectAttributes.addFlashAttribute("message", "文件上传成功");
        return "redirect:/";
    }
  
  	private String generateMd5(String fileName, String expires) {
        try {
        	
            String path = "/downloads/secure/" + fileName;
            
            // 获取远程IP地址
            String remoteIP = getRemoteIP();
            
            String input = secureLinkSecret + path + expires + remoteIP;
            
            String md5 = Base64.encodeBase64URLSafeString(DigestUtils.md5(input));

            return md5;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  	
  	 private static String getBaseName(String strFilePath) {
  		 Path filePath = Paths.get(strFilePath);
         String fileName = filePath.getFileName().toString();
         return fileName;
     }
  	 
    private String getRemoteIP() {
        // 使用Spring框架获取请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
       
        String remoteIP =  requestAttributes.getRequest().getRemoteAddr();
        if(  "0:0:0:0:0:0:0:1".equals(remoteIP)) {
        	remoteIP = "127.0.0.1";
        }
       
        return remoteIP;
    }

    private String generateExpires() {
        // 根据Nginx secure_link配置的过期时间，例如，过期时间设置为1小时后
        long currentTime = System.currentTimeMillis();
        long expires = currentTime + (60 * 60 * 1000); // 1小时，单位是毫秒
        return String.valueOf(expires / 1000); // 转换为Unix时间戳
    }
}
```

在上面的代码中，我们使用`FileObject`类来表示文件对象，然后在`index`方法中生成`FileObject`对象并将它们添加到`fileList`列表。在Thymeleaf视图中，可以使用`th:each`来遍历`fileList`列表并显示文件名、MD5串和expires属性。

**创建Thymeleaf视图：**

创建一个Thymeleaf视图（index.html），用于显示文件列表和生成下载链接:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>文件列表</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4">文件列表</h1>
				<!-- 添加提示信息，使用 Thymeleaf 的 th:if 来控制是否显示 -->
        <div th:if="${message}" class="alert alert-success" role="alert">
            <p th:text="${message}"></p>
        </div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>文件名</th>
                    <th>MD5串</th>
                    <th>Expires</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="file : ${fileList}">
                    <td th:text="${file.fileName}"></td>
                    <td th:text="${file.md5}"></td>
                    <td th:text="${file.expires}"></td>
                    <td>
                        <a th:href="@{'' + ${file.downloadUrl} + '?md5=' + ${file.md5} + '&expires=' + ${file.expires}}" download="downloadedFile.txt" class="btn btn-primary">
                            下载
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>

        <h1>文件上传</h1>
        <form action="/upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">选择文件：</label>
                <input type="file" class="form-control-file" name="file" />
            </div>
            <button type="submit" class="btn btn-success">上传</button>
        </form>
    </div>
</body>
</html>
```

**配置Nginx：**

在Nginx中配置`secure_link`模块，如之前所述。

```nginx
http {

    server {
        listen 80;
        server_name your_server_domain;

       location /downloads/secure/  {
            alias /Users/zjp/Desktop/tmp/;   
            secure_link $arg_md5,$arg_expires;
            secure_link_md5 "your_secret_key_here$uri$arg_expires$remote_addr";
      	    
            if ($secure_link = "") { return 403; }
            if ($secure_link = "0") { return 410; }
        }

        # 其他服务器配置...
    }
}
```

**属性配置：**

在`application.properties` 中设置属性，如上传目录和`secure_link_secret`。

```properties
server.port=8080

# Thymeleaf配置
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML5

# 配置文件上传目录
upload_dir=/path/to/your/uploads/directory
secure_link_secret=your_secret_key_here
download_host=your_download_host

spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
```

请注意，这只是一个起点示例。需要根据具体需求和项目的复杂性进行更多的配置和优化。同时，确保应用程序中包括必要的依赖，如Spring Boot和Thymeleaf。

**启动应用程序：**

运行Spring Boot应用程序，并访问http://localhost:8080/来上传文件，上传成功后，会显示在上面文件列表中。从文件列表中点击下载按钮，可以正常下载就配置正常了。

注意 ：生成的下载链接会有访问IP及时间限制。
