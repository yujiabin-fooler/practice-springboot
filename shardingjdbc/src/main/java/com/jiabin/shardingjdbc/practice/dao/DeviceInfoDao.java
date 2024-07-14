package com.jiabin.shardingjdbc.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.shardingjdbc.practice.model.entity.DeviceInfoEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备信息 DAO
 * @Author jiabin.yu
 * @Date: 2022/4/1
 */
@Repository
public interface DeviceInfoDao extends BaseMapper<DeviceInfoEntity> {
}
