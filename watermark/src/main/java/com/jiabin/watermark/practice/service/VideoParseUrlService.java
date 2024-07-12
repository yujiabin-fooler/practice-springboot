package com.jiabin.watermark.practice.service;


import com.jiabin.watermark.practice.po.ResultDto;

public interface VideoParseUrlService {

    ResultDto dyParseUrl(String redirectUrl) throws Exception;

    ResultDto hsParseUrl(String redirectUrl) throws Exception;

    ResultDto QMParseUrl(String redirectUrl) throws Exception;
}
