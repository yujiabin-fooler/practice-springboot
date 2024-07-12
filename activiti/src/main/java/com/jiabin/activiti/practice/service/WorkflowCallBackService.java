package com.jiabin.activiti.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 工作流业务回调service
 *
 * @author jiabin.yu   2022/3/17 14:02
 */
@Service
@Slf4j
public class WorkflowCallBackService {
    /**
     * 工作流结束回调
     */
    public void doCallBack(String method, String bussinessParams) {
        //TODO 工作流回调的实现
        log.info("工作流结束，回调触发：{}，回调参数：{}", method, bussinessParams);
    }
}
