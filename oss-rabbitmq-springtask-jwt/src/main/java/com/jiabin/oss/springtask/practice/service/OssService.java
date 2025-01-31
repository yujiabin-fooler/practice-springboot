package com.jiabin.oss.springtask.practice.service;

import com.jiabin.oss.springtask.practice.dto.OssCallbackResult;
import com.jiabin.oss.springtask.practice.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * @author jiabin.yu 2018/5/17.
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
