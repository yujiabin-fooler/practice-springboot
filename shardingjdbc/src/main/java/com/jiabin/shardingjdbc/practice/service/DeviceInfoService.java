package com.jiabin.shardingjdbc.practice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiabin.shardingjdbc.practice.model.entity.DeviceInfoEntity;
import com.jiabin.shardingjdbc.practice.model.param.DeviceInfoAddParam;
import com.jiabin.shardingjdbc.practice.model.param.DeviceInfoQueryOneParam;
import com.jiabin.shardingjdbc.practice.model.param.DeviceInfoQueryPageParam;

/**
 * @Description: 设备信息业务接口
 * @Author: junqiang.lu
 * @Date: 2022/4/1
 */
public interface DeviceInfoService {

    /**
     * 设备信息新增
     *
     * @param addParam
     * @return
     */
    DeviceInfoEntity add(DeviceInfoAddParam addParam);

    /**
     * 设备信息查询单条
     *
     * @param queryOneParam
     * @return
     */
    DeviceInfoEntity queryOne(DeviceInfoQueryOneParam queryOneParam);

    /**
     * 设备信息分页查询
     *
     * @param queryPageParam
     * @return
     */
    IPage<DeviceInfoEntity> queryPage(DeviceInfoQueryPageParam queryPageParam);
}
