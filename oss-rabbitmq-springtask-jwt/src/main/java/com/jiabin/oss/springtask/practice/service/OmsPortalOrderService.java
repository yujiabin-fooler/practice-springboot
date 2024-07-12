package com.jiabin.oss.springtask.practice.service;

import com.jiabin.oss.springtask.practice.common.api.CommonResult;
import com.jiabin.oss.springtask.practice.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 * @author jiabin.yu 2018/8/30.
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
