package com.jiabin.springboot.service.practice;

import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.model.practice.vo.article.ArticleListParam;

/**
 * 文章表业务层接口
 * 
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
public interface ArticleService {

	/**
     * 查询列表
     *
     * @param articleListParam
     * @return
     * @throws Exception
     */
	ApiResult list(ArticleListParam articleListParam) throws Exception;

	/**
	 * 查询列表-对比测试-2
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	ApiResult list2(ArticleListParam articleListParam) throws Exception;

	/**
	 * 查询列表-对比测试-3
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	ApiResult list3(ArticleListParam articleListParam) throws Exception;

	/**
	 * 查询列表-对比测试-4
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	ApiResult list4(ArticleListParam articleListParam) throws Exception;


}
