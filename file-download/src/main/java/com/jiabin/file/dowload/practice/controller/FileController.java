package com.jiabin.file.dowload.practice.controller;

import com.jiabin.file.dowload.practice.entity.FileInfo;
import com.jiabin.file.dowload.practice.service.TokenService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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