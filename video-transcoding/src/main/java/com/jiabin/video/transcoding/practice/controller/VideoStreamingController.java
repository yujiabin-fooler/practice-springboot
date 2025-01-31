package com.jiabin.video.transcoding.practice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Controller
public class VideoStreamingController {

    @Value("${transcoded.video.storage.path}")
    private String transcodedVideoStoragePath;

    @GetMapping("/videos/{videoFileName}")
    @ResponseBody
    public FileSystemResource streamVideo(@PathVariable String videoFileName) {
        File videoFile = new File(transcodedVideoStoragePath + File.separator + videoFileName);
        return new FileSystemResource(videoFile);
    }
}