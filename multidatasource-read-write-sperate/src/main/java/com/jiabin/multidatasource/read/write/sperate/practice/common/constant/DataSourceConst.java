package com.jiabin.multidatasource.read.write.sperate.practice.common.constant;

/**
 * @Description: 数据源常量
 * @Author jiabin.yu
 * @Date: 2022/2/14
 */
public class DataSourceConst {

    private DataSourceConst() {
    }

    /**
     * 数据源 bean 名称/key
     * masterDataSource: 主数据源
     * slaveDataSource: 从数据源
     */
    public static final String DATASOURCE_MASTER = "masterDataSource";
    public static final String DATASOURCE_SLAVE = "slaveDataSource";


}
