package com.jiabin.springboot.service.practice.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.common.practice.page.PageUtil;
import com.jiabin.springboot.common.practice.page.QueryUtil;
import com.jiabin.springboot.dao.practice.article.ArticleDao;
import com.jiabin.springboot.model.practice.entity.ArticleEntity;
import com.jiabin.springboot.service.practice.ArticleService;
import com.jiabin.springboot.model.practice.vo.article.ArticleListParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章表业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Service("articleService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 查询列表
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list(ArticleListParam articleListParam) throws Exception {
		long start = System.currentTimeMillis();
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(articleListParam, false, true));
		PageInfo<ArticleEntity> pageInfo = PageHelper.startPage(articleListParam.getCurrentPage(), articleListParam.getPageSize())
				.setOrderBy(articleListParam.getProperties() + " " + articleListParam.getDirection())
				.doSelectPageInfo(() -> articleDao.queryListPage(queryMap));
		long end = System.currentTimeMillis();
		log.info("查询耗时: {}", (end - start));
		return ApiResult.success(pageInfo);
	}

	/**
	 * 查询列表-对比测试-2
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list2(ArticleListParam articleListParam) throws Exception {
		long start = System.currentTimeMillis();
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(articleListParam, false, true));
		PageInfo<ArticleEntity> pageInfo = PageHelper.startPage(articleListParam.getCurrentPage(), articleListParam.getPageSize())
				.setOrderBy(articleListParam.getProperties() + " " + articleListParam.getDirection())
				.doSelectPageInfo(() -> articleDao.queryListPage2(queryMap));
		long end = System.currentTimeMillis();
		log.info("查询耗时: {}", (end - start));
		return ApiResult.success(pageInfo);
	}

	/**
	 * 查询列表-对比测试-3
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list3(ArticleListParam articleListParam) throws Exception {
		long start = System.currentTimeMillis();
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(articleListParam, false, true));
		int countTotal = articleDao.countComplex(queryMap);
		PageUtil pageUtil;
		if (countTotal > 0) {
			List<ArticleEntity> entityList = articleDao.queryListComplex(queryMap);
			pageUtil = new PageUtil(entityList,countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		} else {
			pageUtil = new PageUtil(null, countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		}
		long end = System.currentTimeMillis();
		log.info("查询耗时: {}", (end - start));
		return ApiResult.success(pageUtil);
	}

	/**
	 * 查询列表-对比测试-4
	 *
	 * @param articleListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list4(ArticleListParam articleListParam) throws Exception {
		long start = System.currentTimeMillis();
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(articleListParam, false, true));
		int countTotal = articleDao.countComplex4(queryMap);
		PageUtil pageUtil;
		if (countTotal > 0) {
			List<ArticleEntity> entityList = articleDao.queryListComplex4(queryMap);
			pageUtil = new PageUtil(entityList,countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		} else {
			pageUtil = new PageUtil(null, countTotal, queryMap.getPageSize(), queryMap.getCurrentPage());
		}
		long end = System.currentTimeMillis();
		log.info("查询耗时: {}", (end - start));
		return ApiResult.success(pageUtil);
	}

	
}
