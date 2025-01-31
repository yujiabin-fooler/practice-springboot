package com.jiabin.http.practice.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页数据封装类
 * @author jiabin.yu 2019/4/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;
}
