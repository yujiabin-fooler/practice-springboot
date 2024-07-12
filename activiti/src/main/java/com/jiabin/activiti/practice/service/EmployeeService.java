package com.jiabin.activiti.practice.service;

import com.jiabin.activiti.practice.bo.EmployeeBO;
import com.jiabin.activiti.practice.domain.ActProcess;
import com.jiabin.activiti.practice.domain.AskLeaf;
import com.jiabin.activiti.practice.mapper.ActProcessMapper;
import com.jiabin.activiti.practice.utils.CommonUtil;
import com.jiabin.activiti.practice.utils.WorkflowTypeEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jiabin.yu   2022/3/13 23:38
 */
@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private ActProcessMapper processMapper;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private TaskService taskService;

    /**
     * 员工请假流程
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void askLeaf(EmployeeBO employeeBO) throws Exception {
        //弄个请假的对象
        AskLeaf askLeaf = instanceFromEmployeeBO(employeeBO);
        //保存流程信息
        ActProcess actProcess = instanceFromEmployeeBO(askLeaf);

        //发起一个审批流
        //启动流程，灵活定义的一些参数：审批人、businessKey、processType 等
        Map<String, Object> variables = workflowService.setStartProcess(actProcess, WorkflowTypeEnum.ASK_LEAF);
        //根据给定的参数，流程实例创建
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                //这里其实是工作流的ID
                WorkflowTypeEnum.ASK_LEAF.key,
                //然后保存业务key，对应业务表
                actProcess.getBusinessKey(),
                variables);

        //根据流程实例，拿到对应的任务列表；
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        if (CollectionUtils.isEmpty(taskList)) {
            throw new Exception("请检查流程负责人是否配置齐全！");
        }
        //拿到第一个要执行的任务
        Task task = taskList.get(0);

        //继续补全流程的其余字段
        //流程ID
        actProcess.setInstanceId(processInstance.getProcessInstanceId());
        //当前任务
        actProcess.setCurrentTask(task.getName());
        //下一个要审批的人名字
        actProcess.setCurrentAssignee(task.getAssignee());
        //任务时间
        actProcess.setAcceptTaskTime(task.getCreateTime());
        //最后再保存
        processMapper.insert(actProcess);
        log.info("流程：{}，创建成功。业务编号：{}", WorkflowTypeEnum.ASK_LEAF.name, processInstance.getBusinessKey());
    }


    public ActProcess instanceFromEmployeeBO(AskLeaf askLeaf) {
        ActProcess actProcess = new ActProcess();
        actProcess.setProcessType(WorkflowTypeEnum.ASK_LEAF.value);
        actProcess.setProcessName(WorkflowTypeEnum.ASK_LEAF.name);
        //TODO 这里的UUID，应该要改成业务表数据id，例如 askLeaf.id；至少要有关联
        actProcess.setBusinessKey(commonUtil.uuid());
        actProcess.setApplyUser(commonUtil.getCurrentUsername());
        actProcess.setStartTime(new Date());
        actProcess.setStatus(0);
        actProcess.setApplyReason(askLeaf.getReason());
        actProcess.setCallBackUrl(WorkflowTypeEnum.ASK_LEAF.callbackUrl);
        actProcess.setRemarks(JSONObject.toJSONString(askLeaf));
        return actProcess;
    }

    public AskLeaf instanceFromEmployeeBO(EmployeeBO employeeBO) throws Exception {
        AskLeaf askLeaf = new AskLeaf();
        askLeaf.setApplicant(commonUtil.getCurrentUsername());
        askLeaf.setReason(employeeBO.getReason());
        askLeaf.setApplyTime(new Date());
        askLeaf.setLeafTime(DateUtils.parseDate(employeeBO.getLeafTime(), "yyyy-MM-dd"));
        askLeaf.setStatus("0");
        return askLeaf;
    }

    /**
     * 员工离职
     */
    public void resign(EmployeeBO employeeBO) throws Exception {
        //创建一个 工作流对象
        ActProcess process = new ActProcess();
        workflowService.competeProcess(process, WorkflowTypeEnum.RESIGN);
        process.setBusinessKey(commonUtil.uuid());
        process.setApplyUser(commonUtil.getCurrentUsername());
        process.setApplyReason(employeeBO.getReason());
        process.setRemarks(JSONObject.toJSONString(employeeBO));
        //创建一个工作流
        workflowService.createProcess(process, WorkflowTypeEnum.RESIGN);
    }
}
