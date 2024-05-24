package com.jiabin.xxljob.practice.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyJobHandler {

    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("myJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("job begin ! ");

        System.out.println("执行自定义的任务啦");

        XxlJobHelper.log("job finished !");
    }


}
