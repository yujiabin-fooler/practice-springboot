package com.jiabin.shardingsphere.defaultstrategy.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.shardingsphere.defaultstrategy.practice.entity.CityDict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityDictMapper extends BaseMapper<CityDict> {
}