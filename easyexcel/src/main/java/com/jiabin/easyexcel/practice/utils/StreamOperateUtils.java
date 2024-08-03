package com.jiabin.easyexcel.practice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author jiabin.yu
 * @Description 文件流工具
 * @since 2020-11-05
 */
public class StreamOperateUtils {

    private static final Logger log = LoggerFactory.getLogger(StreamOperateUtils.class);

    /**
     * 复制文件流
     * @param input
     * @return
     */
    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                byteOutStream.write(buffer, 0, len);
            }
            byteOutStream.flush();
            return byteOutStream;
        } catch (IOException e) {
            log.warn("copy InputStream error，{}", e.getMessage());
        }
        return null;
    }

    /**
     * 字节流转输入流
     * @param bytes
     * @return
     */
    public static InputStream byte2InputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 文件流转字节流
     * @param file
     * @return
     */
    public static byte[] file2byte(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return inputStream2byte(fis);
        } catch (IOException e) {
            log.warn("file transfer byte error，{}", e.getMessage());
        } finally {
            try {
                if (fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                log.error("clone inputStream error", e);
            }
        }
        return null;
    }

    /**
     * 输入流转字节流
     * @param in
     * @return
     */
    public static byte[] inputStream2byte(InputStream in) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            in.close();
            bos.close();
            byte[] buffer = bos.toByteArray();
            return buffer;
        } catch (IOException e) {
            log.warn("inputStream transfer byte error，{}", e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("clone inputStream error", e);
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                log.error("clone outputStream error", e);
            }
        }
        return null;
    }
}
