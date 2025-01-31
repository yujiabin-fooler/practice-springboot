/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/10 15:39</create-date>
 *
 * <copyright file="NSDictionary.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.liNSunsoft.com/
 * This source is subject to the LiNSunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.jiabin.hanlp.practice.dictionary.ns;


import com.jiabin.hanlp.practice.corpus.dictionary.item.EnumItem;
import com.jiabin.hanlp.practice.corpus.tag.NS;
import com.jiabin.hanlp.practice.dictionary.common.EnumItemDictionary;

/**
 * 一个好用的地名词典
 *
 * @author hankcs
 */
public class NSDictionary extends EnumItemDictionary<NS>
{
    @Override
    protected NS valueOf(String name)
    {
        return NS.valueOf(name);
    }

    @Override
    protected NS[] values()
    {
        return NS.values();
    }

    @Override
    protected EnumItem<NS> newItem()
    {
        return new EnumItem<NS>();
    }
}
