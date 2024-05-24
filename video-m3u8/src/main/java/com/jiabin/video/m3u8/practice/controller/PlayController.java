package com.jiabin.video.m3u8.practice.controller;

import com.jiabin.video.m3u8.practice.resource.VideoResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/play")
public class PlayController {

	 @Value("${video.output-path}")
	 private String outputDir;
	 

    @GetMapping("/{dirName}/{videoFileName}")
    public ResponseEntity<Resource> playVideo(@PathVariable(name = "dirName") String dirName, @PathVariable(name = "videoFileName") String videoFileName) throws IOException {
    	 String outFilePath = outputDir + File.separator +dirName;
    	String m3u8FilePath = outFilePath +  "/output.m3u8"; // M3U8文件路径
        String tsFilePattern = outFilePath + "/segment-*.ts"; // TS文件通配符路径

        // 读取M3U8文件内容
        String m3u8Content = new String(Files.readAllBytes(Paths.get(m3u8FilePath)));

        // 获取TS文件列表
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:" + tsFilePattern);
        List<Resource> tsResources = Arrays.stream(resources)
                .map(resource -> {
					try {
						return new FileSystemResource(resource.getFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				})
                .collect(Collectors.toList());
        // 设置响应头，指定MIME类型为 application/x-mpegURL
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-mpegURL"));

        // 返回M3U8文件内容和TS文件列表
        return ResponseEntity.ok()
                .headers(headers)
                .body(new VideoResource(m3u8Content, tsResources));
    }
}