package com.jiabin.zip.stream.practice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

@RestController
@RequestMapping("/zip/secured")
public class ZipArchiveSecuredController {

  @GetMapping(value = "/zip-archive", produces = "application/zip")
  public ResponseEntity<StreamingResponseBody> getZipSecured() throws IOException {
    String fileName = URLEncoder.encode("我的压缩文件.zip", "UTF-8");
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName + "\"").body(out -> {
      ZipOutputStream zipOutputStream = new ZipOutputStream(out, "123123".toCharArray());
      addFilesToArchive(zipOutputStream);
    });
  }

  void addFilesToArchive(ZipOutputStream zipOutputStream) throws IOException {
    List<String> files = new ArrayList<>();
    files.add("e:\\zipstream\\1.txt");
    files.add("e:\\zipstream\\2.txt");

    ZipParameters zipParameters = new ZipParameters();
    zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
    zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
    zipParameters.setEncryptFiles(true);

    for (String f : files) {
      File file = new File(f);

      zipParameters.setFileNameInZip(file.getName());
      zipOutputStream.putNextEntry(zipParameters);

      FileInputStream fileInputStream = new FileInputStream(file);
      IOUtils.copy(fileInputStream, zipOutputStream);

      fileInputStream.close();
      zipOutputStream.closeEntry();
    }

    zipOutputStream.flush();
    IOUtils.closeQuietly(zipOutputStream);
  }
}