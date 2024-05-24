package com.jiabin.video.m3u8.practice.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@CrossOrigin
@Controller
public class VideoController {

    @Value("${video.upload-path}")
    private String uploadPath;
    
    @Value("${ffmpeg.path}")
    private String ffmpegPath;

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
            String videoId = UUID.randomUUID().toString() ;
            String videoFileName = videoId + extension;
            String videoFilePath = uploadPath + File.separator + videoFileName;
            File m3u8Dir = new File( outputDir +  File.separator + videoId);
            if( !m3u8Dir.exists() ) {
            	m3u8Dir.mkdirs();
            }
            // 保存上传的视频文件
            File videoFile = new File(videoFilePath);
            file.transferTo(videoFile);
           String tsOutputDir = outputDir + File.separator;
            // 使用FFmpeg将视频转换为M3U8切片
            String command = ffmpegPath + " -i " + videoFilePath + " -hls_time 10 -hls_list_size 0 -hls_segment_filename " +  tsOutputDir +videoId  + "/segment-%03d.ts -hls_base_url /play/"+ videoId + "/  " + tsOutputDir +videoId + "/output.m3u8";
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
        model.addAttribute("videoFileName","/play/" + FilenameUtils.getBaseName(videoFileName) + "/" + FilenameUtils.getBaseName(videoFileName) + ".m3u8");
        return "play";
    }
  
  /*  @GetMapping("/play/m3u8/{videoFileName}")
    public ResponseEntity<Resource> playM3u8(@PathVariable String videoFileName) throws IOException 		{
        String m3u8FilePath = outputDir + File.separator + FilenameUtils.getBaseName(videoFileName) + "/output.m3u8";
       
        Resource videoResource = new FileSystemResource(m3u8FilePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-mpegURL"));
//        headers.setContentDispositionFormData("attachment", videoFileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(videoResource);
    }
    @GetMapping("/play/ts/{dirName}/{videoFileName}")
    public ResponseEntity<Resource> playTs(@PathVariable(name = "dirName") String dirName, @PathVariable(name = "videoFileName") String videoFileName) throws IOException 		{
    	String m3u8FilePath =outputDir + File.separator + dirName +  File.separator + videoFileName;
    	Resource videoResource = new FileSystemResource(m3u8FilePath);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	headers.setContentDispositionFormData("attachment", videoFileName);
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.body(videoResource);
    }*/
}