package com.jiabin.activiti.practice.service;

import com.jiabin.activiti.practice.bo.CompleteTaskDTO;
import com.jiabin.activiti.practice.config.PermissionsConfiguration;
import com.jiabin.activiti.practice.domain.ActProcess;
import com.jiabin.activiti.practice.mapper.ActProcessMapper;
import com.jiabin.activiti.practice.utils.CommonUtil;
import com.jiabin.activiti.practice.utils.TaskTypeEnum;
import com.jiabin.activiti.practice.utils.WorkflowConstants;
import com.jiabin.activiti.practice.utils.WorkflowTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shiva   2022/3/16 11:15
 */
@Slf4j
@Service
public class WorkflowService extends ServiceImpl<ActProcessMapper, ActProcess> {

    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private WorkflowCallBackService workflowCallBackService;
    @Autowired
    private ActProcessMapper processMapper;

    /**
     * 流程列表查询、
     * 流程查询会先查询 act_process 表
     */
    public List<ActProcess> list(ActProcess process) {
        //TODO 分页也暂时不做了
        QueryWrapper<ActProcess> queryWrapper = new QueryWrapper<>();

        // 我发起-已处理任务
        if (Objects.equals(TaskTypeEnum.MY_FINISH_TASK.index, process.getTaskType())) {
            queryWrapper.eq("apply_user", commonUtil.getCurrentUsername());
            queryWrapper.ne("status", 0);
        }
        // 我要处理-待办任务
        if (Objects.equals(TaskTypeEnum.TODO_TASK.index, process.getTaskType())) {
            if (process.getApplyUser() != null) {
                queryWrapper.like("apply_user", process.getApplyUser());
            }
            queryWrapper.like("current_assignee", commonUtil.getCurrentUsername());
            queryWrapper.eq("status", 0);
        }
        // 我要处理-已办任务
        if (Objects.equals(TaskTypeEnum.COMPLETE_TASK.index, process.getTaskType())) {
            if (process.getApplyUser() != null) {
                queryWrapper.like("apply_user", process.getApplyUser());
            }
            queryWrapper.like("actual_assignee_list", commonUtil.getCurrentUsername());
            queryWrapper.ne("status", 0);
        }
        // 所有工作流程
        if (Objects.equals(TaskTypeEnum.ALL_TASK.index, process.getTaskType())) {
            if (process.getApplyUser() != null) {
                queryWrapper.like("apply_user", process.getApplyUser());
            }
            if (process.getStatus() != null) {
                queryWrapper.eq("status", process.getStatus());
            }
        }
        List<ActProcess> list = list(queryWrapper);

        return list;
    }

    /**
     * 办理任务
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public List<Task> competeTask(CompleteTaskDTO completeTaskDTO) throws Exception {
        // 审核意见 同意/不同意
        Integer isAgree = completeTaskDTO.getIsAgree();
        // 审批意见
        String comment = completeTaskDTO.getComment();
        // 审批人
        String confirmAssignee = completeTaskDTO.getAssignee();
        // 审批参数
        Map<String, Object> variables = completeTaskDTO.getVariables() == null ? new HashMap<>() : completeTaskDTO.getVariables();

        //添加是否同意到审批参数
        variables.put(WorkflowConstants.VARIABLE_IS_AGREE, isAgree);
        // 查询待办任务
        List<Task> tasks = getTaskByBusinessKey(completeTaskDTO.getBusinessKey(), completeTaskDTO.getProcessType());
        // 从任务列表中，获得审批人包含 confirmAssignee 的任务
        List<Task> currentTasks = tasks.stream().filter(task -> task.getAssignee().contains(confirmAssignee)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(currentTasks)) {
            throw new Exception("当前操作人(" + confirmAssignee + ")不具备该节点审核资格！");
        }

        // 查询当前办理人（当前任务的办理人）
        Task task = currentTasks.get(0);
        Map<String, Object> existVariables = task.getProcessVariables();

        //节点完成
        completeTask(task, comment, variables);
        // 拿到后续的任务
        List<Task> nextTasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
        //如果不存在下一个任务，那就可以调用结束回调方法
        if (CollectionUtils.isEmpty(nextTasks)) {
            // 调用业务回调
            String callbackUrl = (String) existVariables.get(WorkflowConstants.VARIABLE_CALLBACK_URL);
            String businessKey = (String) existVariables.get(WorkflowConstants.VARIABLE_BUSINESS_KEY);
            if (StringUtils.isNotEmpty(callbackUrl) && StringUtils.isNotEmpty(businessKey)) {
                //TODO 这里可以进行业务回调，回调 URL 可以是消息、方法、和接口
                workflowCallBackService.doCallBack(callbackUrl, businessKey);
            }
        }
        // 如果本次审核被拒绝，或者流程已经结束，相当于流程已经结束
        if (isAgree == 0 || CollectionUtils.isEmpty(nextTasks)) {
            // 更新流程记录
            completeWorkProcess(completeTaskDTO, task);
            return null;
        }
        return nextTasks;
    }

    /**
     * 完成任务，更新 actprocess 表数据
     */
    private void completeWorkProcess(CompleteTaskDTO completeTaskDTO, Task task) {
        //根据实例id，获得 process 数据
        QueryWrapper<ActProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("instance_id", task.getProcessInstanceId());
        ActProcess process = this.baseMapper.selectOne(queryWrapper);
        //整个流程都结束了，最终审批结果，然后整理下工作流的字段
        process.setIsAgree(completeTaskDTO.getIsAgree());
        process.setFinalAssignee(completeTaskDTO.getAssignee());
        process.setCurrentTask("");
        process.setCurrentAssignee("");
        String actualAssigneeList = process.getActualAssigneeList();
        actualAssigneeList = StringUtils.isEmpty(actualAssigneeList) ? "" : (actualAssigneeList + ",");
        process.setActualAssigneeList(actualAssigneeList + completeTaskDTO.getAssignee() + "-" + completeTaskDTO.getIsAgree() + ";");
        process.setStatus(1);
        if (completeTaskDTO.getIsAgree() == 0) {
            process.setStatus(2);
        }
        process.setEndTime(new Date());
        this.baseMapper.updateById(process);
    }

    /**
     * 根据businessKey查询任务
     */
    private List<Task> getTaskByBusinessKey(String businessKey, Integer processType) {
        // 根据业务编号、工作流类型，查询任务列表
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceBusinessKey(businessKey)
                .variableValueEquals(WorkflowConstants.VARIABLE_PROCESS_TYPE, processType)
                .singleResult();
        return taskService.createTaskQuery()
                .processInstanceId(execution.getProcessInstanceId())
                .includeProcessVariables()
                .list();
    }

    /**
     * 根据任务对象完成任务
     */
    private void completeTask(Task task, String comment, Map<String, Object> variables) {
        //添加批注
        Authentication.setAuthenticatedUserId(task.getAssignee());
        //默认批注
        comment = StringUtils.isEmpty(comment) ? WorkflowConstants.COMMENT_DEFAULT : comment;
        Integer isAgree = (Integer) variables.get(WorkflowConstants.VARIABLE_IS_AGREE);
        if (isAgree == WorkflowConstants.REFUSE) {
            comment = WorkflowConstants.COMMENT_REFUSE + "，" + comment;
        } else {
            comment = WorkflowConstants.COMMENT_AGREE + "，" + comment;
        }
        //添加批注
        taskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
        //完成任务(varibles增量更新变量)
        taskService.complete(task.getId(), variables);
    }

    /**
     * 启动一个流程时，有些参数需要动态返回
     */
    public Map<String, Object> setStartProcess(ActProcess process, WorkflowTypeEnum workflowTypeEnum) {
        Map<String, Object> variables = new HashMap<>();
        //申请人
        variables.put(WorkflowConstants.VARIABLE_APPLY_USER, process.getApplyUser());
        //申请理由
        variables.put(WorkflowConstants.VARIABLE_APPLY_REASON, process.getApplyReason());
        //申请类型
        variables.put(WorkflowConstants.VARIABLE_PROCESS_TYPE, process.getProcessType());
        //同意
        variables.put(WorkflowConstants.VARIABLE_IS_AGREE, WorkflowConstants.AGREE);
        //业务key
        variables.put(WorkflowConstants.VARIABLE_BUSINESS_KEY, process.getBusinessKey());
        //关联业务单号
        variables.put(WorkflowConstants.VARIABLE_BILL_NO, process.getRelationBusNo());
        //添加回调方法
        variables.put(WorkflowConstants.VARIABLE_CALLBACK_URL, process.getCallBackUrl());

        //拿到对应流程的审批路线，审批角色列表
        String[] roleCodes = workflowTypeEnum.roles.split(",");

        for (String roleCode : roleCodes) {
            if (StringUtils.isEmpty(roleCode)) {
                continue;
            }
            // TODO 这里肯定要根据实际业务系统修改，目前的用户都是写死的
            // 正常的业务系统中，同一个角色会存在多个人，会有多人同时存在审批权限
            List<String> assigneeNames = new ArrayList<>();
            String[][] usersGroupsAndRoles = PermissionsConfiguration.USERS_GROUPS_AND_ROLES;
            for (String[] usersGroupsAndRole : usersGroupsAndRoles) {
                if (roleCode.equals(usersGroupsAndRole[2])) {
                    //查询到了，拥有指定角色的人员
                    assigneeNames.add(usersGroupsAndRole[0]);
                }
            }
            variables.put(WorkflowConstants.ROLE_TABLE.get(roleCode), assigneeNames);
        }
        // 最后返回全部的流程启动参数
        return variables;
    }

    /**
     * 如果还有后续任务，那就要把后续任务的字段添加到 actprocess 表记录中
     */
    public void updateProcess(List<Task> nextTasks, CompleteTaskDTO completeTaskDTO) {
        // 拿到 process 对象
        Task nextTask = nextTasks.get(0);
        QueryWrapper<ActProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("instance_id", nextTask.getProcessInstanceId());
        ActProcess process = this.baseMapper.selectOne(queryWrapper);
        //更新字段
        process.setCurrentAssignee(nextTask.getAssignee());
        process.setCurrentTask(nextTask.getName());
        process.setAcceptTaskTime(new Date());
        //下一步需要多人同时审批
        if (nextTasks.size() > 1) {
            String assignees = nextTasks.stream().map(Task::getAssignee).collect(Collectors.toList()).toString();
            process.setCurrentAssignee(assignees + "-会签");
        }
        String actualAssigneeList = process.getActualAssigneeList();
        actualAssigneeList = StringUtils.isEmpty(actualAssigneeList) ? "" : (actualAssigneeList);
        process.setActualAssigneeList(actualAssigneeList + completeTaskDTO.getAssignee() + "-" + completeTaskDTO.getIsAgree() + ";");
        updateById(process);
    }

    /**
     * 将预设工作流的字段补充到工作流对象中
     */
    public void competeProcess(ActProcess process, WorkflowTypeEnum workflowTypeEnum) {
        process.setProcessType(workflowTypeEnum.value);
        process.setProcessName(workflowTypeEnum.name);
        process.setStartTime(new Date());
        process.setStatus(0);
        process.setCallBackUrl(workflowTypeEnum.callbackUrl);
    }

    /**
     * 根据业务工作流对象、以及预设工作流，来创建审批流
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void createProcess(ActProcess actProcess, WorkflowTypeEnum workflowTypeEnum) throws Exception {
        //发起一个审批流
        //启动流程，灵活定义的一些参数：审批人、businessKey、processType 等
        Map<String, Object> variables = setStartProcess(actProcess, workflowTypeEnum);
        //根据给定的参数，流程实例创建
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                //这里其实是工作流的ID
                workflowTypeEnum.key,
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
        log.info("流程：{}，创建成功。业务编号：{}", workflowTypeEnum.name, processInstance.getBusinessKey());
    }

}
