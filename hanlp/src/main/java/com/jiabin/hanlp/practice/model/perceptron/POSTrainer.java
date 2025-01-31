/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2017-10-26 下午9:12</create-date>
 *
 * <copyright file="POSTrainer.java" company="码农场">
 * Copyright (c) 2017, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.jiabin.hanlp.practice.model.perceptron;

import com.jiabin.hanlp.practice.model.perceptron.feature.FeatureMap;
import com.jiabin.hanlp.practice.model.perceptron.instance.Instance;
import com.jiabin.hanlp.practice.model.perceptron.instance.POSInstance;
import com.jiabin.hanlp.practice.model.perceptron.tagset.POSTagSet;
import com.jiabin.hanlp.practice.model.perceptron.tagset.TagSet;
import com.jiabin.hanlp.practice.corpus.document.sentence.Sentence;

import java.io.IOException;

/**
 * @author hankcs
 */
public class POSTrainer extends PerceptronTrainer
{
    @Override
    protected TagSet createTagSet()
    {
        return new POSTagSet();
    }

    @Override
    protected Instance createInstance(Sentence sentence, FeatureMap featureMap)
    {
        return POSInstance.create(sentence, featureMap);
    }

    @Override
    public Result train(String trainingFile, String developFile, String modelFile) throws IOException
    {
        // 词性标注模型压缩会显著降低效果
        return train(trainingFile, developFile, modelFile, 0, 10, Runtime.getRuntime().availableProcessors());
    }
}
