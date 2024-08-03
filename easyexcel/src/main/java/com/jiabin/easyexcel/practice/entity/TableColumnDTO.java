package com.jiabin.easyexcel.practice.entity;

import java.io.Serializable;

/**
 * @author jiabin.yu
 * @since 2021-04-15
 */
public class TableColumnDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 列位（第几列）
     */
    private Integer index;

    /**
     * 列名
     */
    private String name;

    /**
     * 列键
     */
    private String key;


    public Integer getIndex() {
        return index;
    }

    public TableColumnDTO setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public String getName() {
        return name;
    }

    public TableColumnDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public TableColumnDTO setKey(String key) {
        this.key = key;
        return this;
    }
}



