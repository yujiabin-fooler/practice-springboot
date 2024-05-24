package com.jiabin.activiti.practice.config;

import com.jiabin.activiti.practice.utils.WorkflowConstants;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 任务跳过监听器
 */
@Component
@Slf4j
public class StartListener implements ExecutionListener {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 3599293834301636975L;


    @Override
    public void notify(DelegateExecution execution) {
        log.info("监听到启动了一个新实例");
        // 获取流程变量
        Map<String, Object> variables = execution.getVariables();
        // 开启支持跳过表达式
        variables.put(WorkflowConstants.SKIP_EXPRESSION, true);
        // 将修改同步到流程中
        // execution.setTransientVariables(variables);
        // 这种方式也行。直接设置流程变量
        execution.setVariable(WorkflowConstants.SKIP_EXPRESSION, true);
    }
}
