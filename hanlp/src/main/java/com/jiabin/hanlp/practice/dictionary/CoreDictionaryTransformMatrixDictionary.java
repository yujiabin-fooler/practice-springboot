/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/19 14:16</create-date>
 *
 * <copyright file="CoreDictionaryTransformMatrixDictionary.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.jiabin.hanlp.practice.dictionary;

import com.jiabin.hanlp.practice.HanLP;
import com.jiabin.hanlp.practice.corpus.tag.Nature;
import static com.jiabin.hanlp.practice.utility.Predefine.logger;

/**
 * 核心词典词性转移矩阵
 * @author hankcs
 */
public class CoreDictionaryTransformMatrixDictionary
{
    public static TransformMatrix transformMatrixDictionary;
    static
    {
        transformMatrixDictionary = new TransformMatrix(){

            @Override
            public int ordinal(String tag)
            {
                return Nature.create(tag).ordinal();
            }
        };
        long start = System.currentTimeMillis();
        if (!transformMatrixDictionary.load(HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath))
        {
            throw new IllegalArgumentException("加载核心词典词性转移矩阵" + HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath + "失败");
        }
        else
        {
            logger.info("加载核心词典词性转移矩阵" + HanLP.Config.CoreDictionaryTransformMatrixDictionaryPath + "成功，耗时：" + (System.currentTimeMillis() - start) + " ms");
        }
    }
}
