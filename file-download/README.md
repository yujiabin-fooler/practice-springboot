使用 SpringBoot + Thymeleaf + Bootstrap 实现文件上传及下载防盗链功能

以下是一个基于 Spring Boot、Thymeleaf 和 Bootstrap 的文件上传和下载应用的示例代码，包括 pom.xml 依赖、属性配置、上传页面、列表页面视图、上传文件存储目录配置、上传文件和防盗链检查逻辑。

配置pom.xml依赖：

```xml
<!-- Spring Boot Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Bootstrap -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>4.5.2</version>
</dependency>

<!-- File Upload -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

配置文件上传属性：

在`application.properties`文件中添加以下属性配置：

```properties
# 配置文件上传目录
upload.directory=/path/to/upload/directory
allowed.ips=192.168.1.1,10.0.0.0/24,0:0:0:0:0:0:0:1,127.0.0.1

# Thymeleaf配置
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML5

spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
```

创建上传页面（upload.html）：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>文件上传</title>
    <link rel="stylesheet" th:href="@{/webjars/bootstrap/4.5.2/css/bootstrap.min.css}" />
</head>
<body>
    <div class="container">
        <h2>文件上传</h2>
        <form method="POST" th:action="@{/upload}" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">选择文件:</label>
                <input type="file" name="file" class="form-control" id="file">
            </div>
            <button type="submit" class="btn btn-primary">上传文件</button>
        </form>
    </div>
</body>
</html>
```

创建列表页面（list.html）：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>文件列表</title>
    <link rel="stylesheet" th:href="@{/webjars/bootstrap/4.5.2/css/bootstrap.min.css}" />
</head>
<body>
    <div class="container">
        <h2>文件列表</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>文件名</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="file : ${files}">
                    <td th:text="${file.name}"></td>
                    <td>
                        <a th:href="@{'/download/' + ${file.name}(token=${file.token})}" class="btn btn-primary">下载</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```

创建实体类

```java
package com.jiabin.file.dowload.practice.entity;

public class FileInfo {
    private String name;
    private String token; // 添加Token属性

    public FileInfo(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
```

创建服务类 TokenService

```java
package com.jiabin.file.dowload.practice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private Map<String, String> tokenMap = new HashMap<>();

    public void storeToken(String fileName, String token) {
        tokenMap.put(fileName, token);
    }

    public String getToken(String fileName) {
        return tokenMap.get(fileName);
    }
}
```

创建文件上传和防盗链检查逻辑：

```java
package com.jiabin.file.dowload.practice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jiabin.file.dowload.practice.entity.FileInfo;
import com.jiabin.file.dowload.practice.service.TokenService;

@Controller
public class FileController {
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    private TokenService tokenService;

    @Value("${allowed.ips}")
    private String allowedIPs;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                // 防盗链检查逻辑
                if (isAuthorizedToUploadFile()) {
                    File uploadedFile = new File(uploadDirectory, fileName);
                    file.transferTo(uploadedFile);
                    String token = UUID.randomUUID().toString(); // 生成Token
                    tokenService.storeToken(fileName, token); // 存储Token
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listFiles(Model model) {
        File uploadDir = new File(uploadDirectory);
        File[] files = uploadDir.listFiles();
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (File file : files) {
             String fileName = file.getName();
              String token = tokenService.getToken(fileName); // 获取文件对应的Token
              fileInfoList.add(new FileInfo(fileName, token));
        }
        model.addAttribute("files", fileInfoList);
        return "list";
    }

    @GetMapping("/download/{fileName}")
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        if (isAuthorizedToDownloadFile(fileName, token, request.getRemoteAddr())) {
            File file = new File(uploadDirectory, fileName);

            if (file.exists()) {
                try {
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                    response.setContentType("application/octet-stream");
                    response.setContentLengthLong(file.length());

                    FileInputStream inputStream = new FileInputStream(file);
                    OutputStream outputStream = response.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 文件不存在，可以返回适当的错误页面或响应
                // response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            // 返回未授权或其他错误页面
            // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAuthorizedToUploadFile() {
        // 实现防盗链检查逻辑，根据需要
        return true;
    }

    private boolean isAuthorizedToDownloadFile(String fileName, String token, String remoteIP) {
        // 从数据库或内存中获取与文件名关联的Token
        String storedToken = tokenService.getToken(fileName);

        // 验证Token是否匹配，并检查IP和时间限制
        if (storedToken != null && storedToken.equals(token) && isIPAllowed(remoteIP) && isTimeAllowed()) {
            return true;
        }
        return false;
    }

    private boolean isIPAllowed(String remoteIP) {
        // 检查remoteIP是否在允许的IP地址范围内
        List<String> allowedIPsList = Arrays.asList(allowedIPs.split(","));
        try {
            InetAddress inetAddress = InetAddress.getByName(remoteIP);
            for (String allowedIP : allowedIPsList) {
                if (isIPInRange(inetAddress, allowedIP)) {
                    return true;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isIPInRange(InetAddress inetAddress, String allowedIP) {
        if (allowedIP.contains("/")) {
            String[] parts = allowedIP.split("/");
            String ipPrefix = parts[0];
            int prefixLength = Integer.parseInt(parts[1]);
            
            // 忽略 IPv6 本地环回地址
            if (ipPrefix.equals("0:0:0:0:0:0:0:1")) {
                return true;
            }

            InetAddress networkAddress;
            try {
                networkAddress = InetAddress.getByName(ipPrefix);
                byte[] remoteBytes = inetAddress.getAddress();
                byte[] networkBytes = networkAddress.getAddress();

                for (int i = 0; i < prefixLength / 8; i++) {
                    if (remoteBytes[i] != networkBytes[i]) {
                        return false;
                    }
                }

                int remainingBits = prefixLength % 8;
                if (remainingBits > 0) {
                    int mask = 0xFF << (8 - remainingBits);
                    if ((remoteBytes[prefixLength / 8] & mask) != (networkBytes[prefixLength / 8] & mask)) {
                        return false;
                    }
                }

                return true;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }else {
        	if (allowedIP.equals("0:0:0:0:0:0:0:1")) {
                return true;
            }
        }
        return false;
    }

    private boolean isTimeAllowed() {
        // 设置有效时间的开始时间和结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = sdf.parse("2023-01-01 00:00:00");
            endTime = sdf.parse("2023-12-31 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (currentTime.after(startTime) && currentTime.before(endTime)) {
            return true;
        }

        return false;
    }
}
```

这是一个简单的示例，大家需要根据具体需求进一步扩展和完善代码，特别是防盗链检查逻辑。

