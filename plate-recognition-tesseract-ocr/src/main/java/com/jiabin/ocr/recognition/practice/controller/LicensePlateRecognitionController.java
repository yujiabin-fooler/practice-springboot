package com.jiabin.ocr.recognition.practice.controller;

import com.google.common.collect.Sets;
import com.jiabin.ocr.recognition.practice.enumtype.PlateColor;
import com.jiabin.ocr.recognition.practice.util.Constant;
import com.jiabin.ocr.recognition.practice.util.FileUtil;
import com.jiabin.ocr.recognition.practice.util.GenerateIdUtil;
import com.jiabin.ocr.recognition.practice.util.PlateUtil;
import net.sourceforge.tess4j.Tesseract;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

@Controller
public class LicensePlateRecognitionController {

	 static {
	    	OpenCV.loadLocally();
	 }

    @Autowired
    private Tesseract tesseract;
    
     @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }
    
    @PostMapping("/recognize")
    public String recognizeLicensePlate(@RequestParam("imageFile") MultipartFile imageFile, Model model) {
        try {

             File convFile = convert(imageFile);
             String recognizedText =doRecognise(convFile);
            
             model.addAttribute("result", "识别结果：" + recognizedText);

            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    
    /**
     * 单张图片 车牌识别
     * 拷贝文件到临时目录
     * @param f
     * @return 车牌识别 信息
     */
    public String doRecognise(File f) {
        if(!f.exists()) {
            return null;
        }
        
        // 创建临时目录， 存放过程图片
        String ct = GenerateIdUtil.getStrId();
        String tempPath =  Constant.DEFAULT_TEMP_DIR + ct + "/";
        FileUtil.createDir(tempPath);
        String targetPath = Constant.DEFAULT_TEMP_DIR + ct + (f.getName().substring(f.getName().lastIndexOf(".")));
        FileUtil.copyAndRename(f.getAbsolutePath(), targetPath); // 拷贝文件并且重命名

      

        Boolean debug = false;
        Vector<Mat> dst = new Vector<Mat>();
        PlateUtil.getPlateMat(targetPath, dst, debug, tempPath);

        Set<String> plates = Sets.newHashSet();
        dst.stream().forEach(inMat -> {
            PlateColor color = PlateUtil.getPlateColor(inMat, true, false, tempPath);
            String plate = PlateUtil.charsSegment(inMat, color, debug, tempPath);
            plates.add("<" + plate + "," + color.desc + ">");
        });
        
        new File(targetPath).delete();  // 删除拷贝的临时文件
        return plates.toString();
    }
}