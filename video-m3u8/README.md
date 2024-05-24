要实现Spring Boot应用中的视频上传、转换为M3U8切片以及播放M3U8视频功能

要实现Spring Boot应用中的视频上传、转换为M3U8切片以及播放M3U8视频，我们可以使用以下步骤和相应的依赖配置。

### 创建Spring Boot项目

首先，需要创建一个Spring Boot项目。可以使用Spring Initializer（https://start.spring.io/）来方便地生成项目骨架，确保选择Thymeleaf和Web依赖。

### 添加pom.xml依赖

在项目的pom.xml文件中添加以下依赖，以支持视频处理和Web页面渲染：

```xml
<dependencies>
    <!-- Spring Boot Web Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf for HTML templating -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
  
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.13.0</version>
  </dependency>

</dependencies>
```

###  配置 application.properties 

在 application.properties 文件中，配置FFmpeg的可执行文件路径。确保您已经安装了FFmpeg并知道其路径。

```properties
# FFmpeg可执行文件的路径
ffmpeg.path=/path/to/ffmpeg

# 视频上传路径
video.upload-path=/path/to/uploaded/videos/

# 视频输出路径
video.output-path=/path/to/output/directory/

# 文件上传配置
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

# Spring Boot Thymeleaf配置
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false
```

###  创建控制器

创建一个控制器来处理视频上传和M3U8切片转换：

```java
package com.jiabin.video.m3u8.practice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class VideoController {

    @Value("${video.upload-path}")
    private String uploadPath;

    @Value("${video.output-path}")
    private String outputDir;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String videoFileName = UUID.randomUUID().toString() + extension;
            String videoFilePath = uploadPath + videoFileName;

            // 保存上传的视频文件
            File videoFile = new File(videoFilePath);
            file.transferTo(videoFile);
						outputDir = outputDir + File.separator;
            // 使用FFmpeg将视频转换为M3U8切片
            String command = "ffmpeg -i " + videoFilePath + " -hls_time 10 -hls_list_size 0 -hls_segment_filename " + outputDir + "segment-%03d.ts " + outputDir + videoFileName + "/output.m3u8";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            model.addAttribute("videoFileName", videoFileName);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "视频上传和处理出错");
        }
        return "redirect:/play/" + model.getAttribute("videoFileName");
    }
    
    @GetMapping("/play/{videoFileName}")
    public String playVideo(@PathVariable String videoFileName, Model model) {
        model.addAttribute("videoFileName", videoFileName);
        return "play";
    }
  
    @GetMapping("/play/m3u8/{videoFileName}")
    public ResponseEntity<Resource> playM3u8(@PathVariable String videoFileName) throws IOException 		{
        String videoFilePath = outputDir + videoFileName + "/output.m3u8";
        Resource videoResource = new FileSystemResource(videoFilePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", videoFileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(videoResource);
    }
}
```

### 创建HTML模板

创建一个Thymeleaf模板（index.html）来渲染上传表单和视频播放器：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>视频上传和播放</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">视频上传和播放</h1>

        <form th:action="@{/upload}" method="post" enctype="multipart/form-data" class="mt-3">
            <div class="mb-3">
                <input type="file" name="file" accept="video/*" class="form-control">
            </div>
            <div class="mb-3">
                <input type="submit" value="上传视频" class="btn btn-primary">
            </div>
        </form>
      
        <div th:if="${error}" class="alert alert-danger mt-3">
            <p th:text="${error}"></p>
        </div>
    </div>

    <!-- 引入 Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
```

创建一个新的HTML模板（play.html）来渲染M3U8视频：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>播放视频</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">视频播放</h1>

        <video width="640" height="360" controls class="mt-3 mb-3">
            <source th:src="@{'/path/to/output/directory/' + ${videoFileName} + '/output.m3u8'}" type="application/x-mpegURL">
            <!-- 您的浏览器不支持视频标签，请升级您的浏览器或使用支持HTML5视频的浏览器。 -->
            <p>您的浏览器不支持视频标签，请升级您的浏览器或使用支持HTML5视频的浏览器。</p>
        </video>
    </div>

    <!-- 引入 Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/js/bootstrap.min.js"></script>
</body>
</html>

```

现在，我们已经设置了一个Spring Boot应用程序，运行Spring Boot应用程序，并访问 http://localhost:8080/，可以通过上传视频将其转换为M3U8切片并播放它。