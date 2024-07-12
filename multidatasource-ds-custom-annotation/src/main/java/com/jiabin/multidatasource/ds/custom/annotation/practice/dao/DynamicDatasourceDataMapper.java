package com.jiabin.multidatasource.ds.custom.annotation.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.multidatasource.ds.custom.annotation.practice.entity.DynamicDatasourceData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DynamicDatasourceDataMapper extends BaseMapper<DynamicDatasourceData> {
}