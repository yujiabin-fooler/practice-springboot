package com.jiabin.mongodb.mongotemplate.practice.service;

import com.jiabin.mongodb.mongotemplate.practice.common.api.ApiResult;
import com.jiabin.mongodb.mongotemplate.practice.model.param.*;

/**
 * @Description: 博客业务接口
 * @Author jiabin.yu
 * @Date: 2021/11/13
 */
public interface BlogService {

    /**
     * 新增单条博客
     *
     * @param addParam
     * @return
     */
    ApiResult add(BlogAddParam addParam);

    /**
     * 批量新增博客
     *
     * @param addBatchParam
     * @return
     */
    ApiResult addBatch(BlogAddBatchParam addBatchParam);

    /**
     * 查询单条博客
     *
     * @param queryOneParam
     * @return
     */
    ApiResult queryOne(BlogQueryOneParam queryOneParam);

    /**
     * 分页查询博客
     *
     * @param queryPageParam
     * @return
     */
    ApiResult queryPage(BlogQueryPageParam queryPageParam);

    /**
     * 查询博客阅读量
     *
     * @param readCountQueryParam
     * @return
     */
    ApiResult queryReadCount(BlogReadCountQueryParam readCountQueryParam);

    /**
     * 查询博客阅读量统计
     *
     * @param readCountSummaryParam
     * @return
     */
    ApiResult summaryReadCount(BlogReadCountSummaryParam readCountSummaryParam);

    /**
     * 按照作者分组查询博客数据
     *
     * @param queryGroupByAuthorParam
     * @return
     */
    ApiResult queryGroupByAuthor(BlogQueryGroupByAuthorParam queryGroupByAuthorParam);

    /**
     * 按照作者分组分页查询你博客数据
     *
     * @param queryGroupByAuthorPageParam
     * @return
     */
    ApiResult queryGroupByAuthorPage(BlogQueryGroupByAuthorPageParam queryGroupByAuthorPageParam);

    /**
     * 按照客户端时间分组查询博客数据
     *
     * @param queryGroupByClientTimestampParam
     * @return
     */
    ApiResult queryGroupByClientTimestamp(BlogQueryGroupByClientTimestampParam queryGroupByClientTimestampParam);

    /**
     * 按照创建时间分组查询博客数据
     *
     * @param queryGroupByCreateTimeParam
     * @return
     */
    ApiResult queryGroupByCreateTime(BlogQueryGroupByCreateTimeParam queryGroupByCreateTimeParam);

    /**
     * 按照创建时间自定义区间分组查询博客数据
     *
     * @param queryGroupByCreateTimeDiyParam
     * @return
     */
    ApiResult queryGroupByCreateTimeDiy(BlogQueryGroupByCreateTimeDiyParam queryGroupByCreateTimeDiyParam);

    /**
     * 按照更新时间分组查询博客数据
     *
     * @param queryGroupByUpdateTimeParam
     * @return
     */
    ApiResult queryGroupByUpdateTime(BlogQueryGroupByUpdateTimeParam queryGroupByUpdateTimeParam);

    /**
     * 更新单条博客
     *
     * @param updateParam
     * @return
     */
    ApiResult update(BlogUpdateParam updateParam);

    /**
     * 批量更新博客
     *
     * @param updateBatchParam
     * @return
     */
    ApiResult updateBatch(BlogUpdateBatchParam updateBatchParam);

    /**
     * 删除单条博客
     *
     * @param deleteOneParam
     * @return
     */
    ApiResult deleteOne(BlogDeleteOneParam deleteOneParam);

    /**
     * 批量删除博客
     *
     * @param deleteBatchParam
     * @return
     */
    ApiResult deleteBatch(BlogDeleteBatchParam deleteBatchParam);


}
