package com.jiabin.easyexcel.practice.entity;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jiabin.yu
 * @since 2021-04-15
 */
public class CommonEasyExcelImportDTO<T> implements Serializable {

    private static final long serialVersionUID = -1009632170320926168L;

    /**
     * 是否有错误
     */
    private boolean hasError = false;

    /**
     * 错误条数
     */
    private int errorCount =  0;

    /**
     * 原始解析的表头数据
     */
    private List<Map<Integer, String>> headList = new ArrayList<>();

    /**
     * 头部
     */
    private List<TableColumnDTO> columnList = Lists.newArrayList();

    /**
     * 数据体
     */
    private List<T> columnDataList = Lists.newArrayList();

    public boolean getHasError() {
        return hasError;
    }

    public CommonEasyExcelImportDTO setHasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public CommonEasyExcelImportDTO setErrorCount(int errorCount) {
        this.errorCount = errorCount;
        return this;
    }

    public List<Map<Integer, String>> getHeadList() {
        return headList;
    }

    public CommonEasyExcelImportDTO setHeadList(List<Map<Integer, String>> headList) {
        this.headList = headList;
        return this;
    }

    public List<TableColumnDTO> getColumnList() {
        return columnList;
    }

    public CommonEasyExcelImportDTO setColumnList(List<TableColumnDTO> columnList) {
        this.columnList = columnList;
        return this;
    }

    public List<T> getColumnDataList() {
        return columnDataList;
    }

    public CommonEasyExcelImportDTO setColumnDataList(List<T> columnDataList) {
        this.columnDataList = columnDataList;
        return this;
    }
}
