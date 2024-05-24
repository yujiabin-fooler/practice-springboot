package com.jiabin.nginx.file.download.practice.controller;

import com.jiabin.nginx.file.download.practice.entity.FileObject;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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