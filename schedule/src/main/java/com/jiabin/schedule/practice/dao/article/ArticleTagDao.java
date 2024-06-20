package com.jiabin.schedule.practice.dao.article;

import com.jiabin.schedule.practice.dao.BaseDao;
import com.jiabin.schedule.practice.model.entity.ArticleTagEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章标签表
 * 
 * @author junqiang.lu
 * @date 2019-11-25 14:01:38
 */
@Repository
public interface ArticleTagDao extends BaseDao<ArticleTagEntity> {

    /**
     * 查询某一篇文章的标签(列表)
     *
     * @param articleId
     * @return
     */
    List<ArticleTagEntity> queryTagsByArticleId(@Param("articleId") long articleId);

}
