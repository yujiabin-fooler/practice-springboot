package com.jiabin.fastmybatis.practice.controller;

import com.gitee.fastmybatis.core.query.annotation.Condition;
import com.gitee.fastmybatis.core.query.param.PageMultiSortParam;

public class UserParam extends PageMultiSortParam {

    @Condition
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
