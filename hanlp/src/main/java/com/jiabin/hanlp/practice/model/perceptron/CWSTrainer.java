/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-09-04 PM4:48</create-date>
 *
 * <copyright file="PerceptronSegmentTrainer.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.jiabin.hanlp.practice.model.perceptron;

import com.jiabin.hanlp.practice.model.perceptron.feature.FeatureMap;
import com.jiabin.hanlp.practice.model.perceptron.instance.CWSInstance;
import com.jiabin.hanlp.practice.model.perceptron.model.LinearModel;
import com.jiabin.hanlp.practice.model.perceptron.tagset.TagSet;
import com.jiabin.hanlp.practice.model.perceptron.utility.Utility;
import com.jiabin.hanlp.practice.model.perceptron.tagset.CWSTagSet;
import com.jiabin.hanlp.practice.model.perceptron.instance.Instance;
import com.jiabin.hanlp.practice.corpus.document.sentence.Sentence;
import com.jiabin.hanlp.practice.corpus.document.sentence.word.Word;

import java.io.IOException;
import java.util.List;

/**
 * 感知机分词器训练工具
 *
 * @author hankcs
 */
public class CWSTrainer extends PerceptronTrainer
{
    @Override
    protected TagSet createTagSet()
    {
        return new CWSTagSet();
    }

    @Override
    protected Instance createInstance(Sentence sentence, FeatureMap mutableFeatureMap)
    {
        List<Word> wordList = sentence.toSimpleWordList();
        String[] termArray = Utility.toWordArray(wordList);
        Instance instance = new CWSInstance(termArray, mutableFeatureMap);
        return instance;
    }

    @Override
    public double[] evaluate(String developFile, LinearModel model) throws IOException
    {
        PerceptronSegmenter segmenter = new PerceptronSegmenter(model);
        double[] prf = Utility.prf(Utility.evaluateCWS(developFile, segmenter));
        return prf;
    }

}
