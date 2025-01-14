/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 3:11</create-date>
 *
 * <copyright file="EasyDictionary.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.jiabin.hanlp.practice.corpus.dictionary;

import com.jiabin.hanlp.practice.collection.trie.DoubleArrayTrie;
import com.jiabin.hanlp.practice.dictionary.BaseSearcher;
import com.jiabin.hanlp.practice.corpus.tag.Nature;
import com.jiabin.hanlp.practice.HanLP;
import com.jiabin.hanlp.practice.utility.Predefine;

import java.io.*;
import java.util.*;

/**
 * 一个通用的、满足特定格式的双数组词典
 *
 * @author hankcs
 */
public class EasyDictionary
{
    DoubleArrayTrie<Attribute> trie = new DoubleArrayTrie<Attribute>();

    public static EasyDictionary create(String path)
    {
        EasyDictionary dictionary = new EasyDictionary();
        if (dictionary.load(path))
        {
            return dictionary;
        }
        else
        {
            Predefine.logger.warning("从" + path + "读取失败");
        }

        return null;
    }

    private boolean load(String path)
    {
        Predefine.logger.info("通用词典开始加载:" + path);
        TreeMap<String, Attribute> map = new TreeMap<String, Attribute>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(HanLP.Config.IOAdapter == null ? new FileInputStream(path) : HanLP.Config.IOAdapter.open(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                String param[] = line.split("\\s+");
                int natureCount = (param.length - 1) / 2;
                Attribute attribute = new Attribute(natureCount);
                for (int i = 0; i < natureCount; ++i)
                {
                    attribute.nature[i] = Nature.create(param[1 + 2 * i]);
                    attribute.frequency[i] = Integer.parseInt(param[2 + 2 * i]);
                    attribute.totalFrequency += attribute.frequency[i];
                }
                map.put(param[0], attribute);
            }
            Predefine.logger.info("通用词典读入词条" + map.size());
            br.close();
        }
        catch (FileNotFoundException e)
        {
            Predefine.logger.severe("通用词典" + path + "不存在！" + e);
            return false;
        }
        catch (IOException e)
        {
            Predefine.logger.severe("通用词典" + path + "读取错误！" + e);
            return false;
        }

        Predefine.logger.info("通用词典DAT构建结果:" + trie.build(map));
        Predefine.logger.info("通用词典加载成功:" + trie.size() +"个词条" );
        return true;
    }

    public Attribute GetWordInfo(String key)
    {
        return trie.get(key);
    }

    public boolean contains(String key)
    {
        return GetWordInfo(key) != null;
    }

    public BaseSearcher getSearcher(String text)
    {
        return new Searcher(text);
    }

    public class Searcher extends BaseSearcher<Attribute>
    {
        /**
         * 分词从何处开始，这是一个状态
         */
        int begin;

        private List<Map.Entry<String, Attribute>> entryList;

        protected Searcher(char[] c)
        {
            super(c);
        }

        protected Searcher(String text)
        {
            super(text);
            entryList = new LinkedList<Map.Entry<String, Attribute>>();
        }

        @Override
        public Map.Entry<String, Attribute> next()
        {
            // 保证首次调用找到一个词语
            while (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            // 之后调用仅在缓存用完的时候调用一次
            if (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            if (entryList.size() == 0)
            {
                return null;
            }
            Map.Entry<String, Attribute> result = entryList.get(0);
            entryList.remove(0);
            offset = begin - 1;
            return result;
        }
    }

    /**
     * 通用词典中的词属性
     */
    static public class Attribute
    {
        /**
         * 词性列表
         */
        public Nature nature[];
        /**
         * 词性对应的词频
         */
        public int frequency[];

        public int totalFrequency;

        public Attribute(int size)
        {
            nature = new Nature[size];
            frequency = new int[size];
        }

        public Attribute(Nature[] nature, int[] frequency)
        {
            this.nature = nature;
            this.frequency = frequency;
        }

        public Attribute(Nature nature, int frequency)
        {
            this(1);
            this.nature[0] = nature;
            this.frequency[0] = frequency;
            totalFrequency = frequency;
        }

        /**
         * 使用单个词性，默认词频1000构造
         *
         * @param nature
         */
        public Attribute(Nature nature)
        {
            this(nature, 1000);
        }

        /**
         * 获取词性的词频
         *
         * @param nature 字符串词性
         * @return 词频
         * @deprecated 推荐使用Nature参数！
         */
        public int getNatureFrequency(String nature)
        {
            try
            {
                Nature pos = Nature.create(nature);
                return getNatureFrequency(pos);
            }
            catch (IllegalArgumentException e)
            {
                return 0;
            }
        }

        /**
         * 获取词性的词频
         *
         * @param nature 词性
         * @return 词频
         */
        public int getNatureFrequency(final Nature nature)
        {
            int result = 0;
            int i = 0;
            for (Nature pos : this.nature)
            {
                if (nature == pos)
                {
                    return frequency[i];
                }
                ++i;
            }
            return result;
        }

        @Override
        public String toString()
        {
            return "Attribute{" +
                    "nature=" + Arrays.toString(nature) +
                    ", frequency=" + Arrays.toString(frequency) +
                    '}';
        }
    }
}
