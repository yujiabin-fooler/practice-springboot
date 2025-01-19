package com.jiabin.zip.stream.practice.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/zip")
public class ZipArchiveController {

  @GetMapping(value = "/zip-archive", produces = "application/zip")
  public ResponseEntity<byte[]> getZipBytes() throws IOException {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
    ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

    addFilesToArchive(zipOutputStream);

    IOUtils.closeQuietly(bufferedOutputStream);
    IOUtils.closeQuietly(byteArrayOutputStream);

    String fileName = URLEncoder.encode("我的压缩文件.zip", "UTF-8");

    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
        .body(byteArrayOutputStream.toByteArray());
  }

  /**
   * 对于较大的压缩文件，我们应避免将所有内容加载到内存中。 相反，我们可以在创建 ZIP 文件时将其直接流式传输到客户端。 这样可以减少内存消耗，并让我们能高效地为庞大的文件提供服务。
   * @return
   * @throws Exception
   */
  @GetMapping(value = "/zip-archive-stream", produces = "application/zip")
  public ResponseEntity<StreamingResponseBody> getZipStream() throws Exception {
    String fileName = URLEncoder.encode("我的压缩文件.zip", "UTF-8") ;
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName + "\"").body(out -> {
      ZipOutputStream zipOutputStream = new ZipOutputStream(out) ;
      // 设置压缩级别
      zipOutputStream.setLevel(9) ;
      addFilesToArchive(zipOutputStream);
    });
  }

  void addFilesToArchive(ZipOutputStream zipOutputStream) throws IOException {
    List<String> files = new ArrayList<>();
    files.add("e:\\zipstream\\1.txt");
    files.add("e:\\zipstream\\2.txt");

    for (String f : files) {
      File file = new File(f);
      zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
      FileInputStream fileInputStream = new FileInputStream(file);

      IOUtils.copy(fileInputStream, zipOutputStream);

      fileInputStream.close();
      zipOutputStream.closeEntry();
    }

    zipOutputStream.finish();
    zipOutputStream.flush();
    IOUtils.closeQuietly(zipOutputStream);
  }
}