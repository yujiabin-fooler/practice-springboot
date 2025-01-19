package com.jiabin.zip.stream.practice.controller;

import com.jiabin.zip.stream.practice.domain.User;
import com.jiabin.zip.stream.practice.jdbc.UserService;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/compress")
public class CompressController {

  private final UserService userService;

  public CompressController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/zip")
  public ResponseEntity<byte[]> zip() throws Exception {
    String fileName = URLEncoder.encode("我的压缩文件.zip", "UTF-8") ;
    ByteArrayOutputStream baos = genStream() ;
    
    ByteArrayOutputStream stream = createZipFileInMemory(baos) ;
    
    return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
        .body(stream.toByteArray()) ;
  }

  public ByteArrayOutputStream createZipFileInMemory(ByteArrayOutputStream content) throws IOException {
    ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
    try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(zipStream)) {
      ZipArchiveEntry zipEntry = new ZipArchiveEntry("users.txt") ;
      zipOut.putArchiveEntry(zipEntry);
      IOUtils.copy(new ByteArrayInputStream(content.toByteArray()), zipOut) ;
      zipOut.closeArchiveEntry();
    }
    return zipStream;
  }
  
  public ByteArrayOutputStream genStream() throws Exception {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"))) {
      List<User> users = this.userService.list() ;
      for (User user : users) {
        writer.println(user.toString()) ;
      }
    }
    return os ;
  }

}
