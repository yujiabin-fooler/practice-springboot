package com.jiabin.multidatasource.read.write.sperate.practice.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description: 路由数据库
 * @Author jiabin.yu
 * @Date: 2022/2/14
 */
public class RoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceContext.get();
    }
}
