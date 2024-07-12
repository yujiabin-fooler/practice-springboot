package com.jiabin.activiti.practice.web;

import com.jiabin.activiti.practice.bo.CompleteTaskDTO;
import com.jiabin.activiti.practice.bo.EmployeeBO;
import com.jiabin.activiti.practice.config.SecurityUtil;
import com.jiabin.activiti.practice.core.Response;
import com.jiabin.activiti.practice.domain.ActProcess;
import com.jiabin.activiti.practice.service.EmployeeService;
import com.jiabin.activiti.practice.service.WorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 相关流程接口
 *
 * @author jiabin.yu   2022/3/12 14:44
 */
@Slf4j
@RestController
@RequestMapping
public class ActivitiController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 员工发起请假接口
     */
    @RequestMapping(value = "/employee/askLeaf")
    public Response empAskLeaf(EmployeeBO employeeBO) throws Exception {
        //先登录，然后就有了当前用户
        securityUtil.logInAs(employeeBO.getLoginAs());
        //然后申请请假
        employeeService.askLeaf(employeeBO);
        return Response.builder().code(0).build();
    }

    /**
     * 员工发起离职接口
     */
    @RequestMapping(value = "/employee/resign")
    public Response empResign(EmployeeBO employeeBO) throws Exception {
        //先登录，然后就有了当前用户
        securityUtil.logInAs(employeeBO.getLoginAs());
        //然后申请请假
        employeeService.resign(employeeBO);
        return Response.builder().code(0).build();
    }

    /**
     * 查询自己的审批任务
     */
    @RequestMapping(value = "/process/listProcess")
    public Response listProcess(String loginAs, String taskType) throws Exception {
        securityUtil.logInAs(loginAs);
        //查询类型，详见 TaskTypeEnum
        ActProcess actProcess = new ActProcess();
        actProcess.setTaskType(Integer.valueOf(taskType));
        List<ActProcess> list = workflowService.list(actProcess);
        return Response.builder().code(0).data(list).build();
    }

    /**
     * 流程，根据流程ID，同意完成审批
     */
    @RequestMapping(value = "/process/submitProcess")
    public Response submitProcess(String loginAs, String processId, String isAgree, String comment) throws Exception {
        securityUtil.logInAs(loginAs);
        //这里其实是，拿到 业务流程id
        //再组装完成任务需要的字段内容
        ActProcess process = workflowService.getById(processId);
        CompleteTaskDTO completeTaskDTO = new CompleteTaskDTO();
        completeTaskDTO.setBusinessKey(process.getBusinessKey());
        completeTaskDTO.setProcessType(process.getProcessType());
        completeTaskDTO.setIsAgree(Integer.valueOf(isAgree));
        completeTaskDTO.setComment(comment);
        completeTaskDTO.setAssignee(loginAs);

        //调用另一个方法，因为要用到递归，所以单独提取出来
        boolean result = completeTask(completeTaskDTO);
        // 返回值
        return Response.builder().code(result ? 0 : 1).build();
    }

    private boolean completeTask(CompleteTaskDTO completeTaskDTO) throws Exception {
        //然后完成任务，业务actprocess 对应的当前任务默认封装在 service里
        List<Task> nextTasks = workflowService.competeTask(completeTaskDTO);
        // 还存在后续任务，那就需要更新当前 actProcess 的字段，而不是全部结束
        if (!CollectionUtils.isEmpty(nextTasks)) {
            workflowService.updateProcess(nextTasks, completeTaskDTO);
        }
        // 下一个节点处理人和当前节点处理人属于同一个 自动处理
        if (!CollectionUtils.isEmpty(nextTasks)) {
            String confirmAssignee = completeTaskDTO.getAssignee();
            List<Task> nextTodoList = nextTasks.stream().filter(nextTask -> nextTask.getAssignee().contains(confirmAssignee)).limit(1).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(nextTodoList)) {
                log.info("【审核跳过】：{}", nextTodoList);
                completeTaskDTO.setComment("审批连续节点-自动同意");
                completeTask(completeTaskDTO);
            }
        }
        return true;
    }


    /**
     * 挂起流程
     * @param status 0-恢复，1-挂起
     */
    @RequestMapping(value = "/process/processSuspend")
    public Response processSuspend(String processKey, String status) {
        //拿到指定的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processKey).singleResult();
        // 得到当前流程定义的实例是否都为暂停状态
        boolean suspended = processDefinition.isSuspended();
        //拿到流程定义ID
        String processId = processDefinition.getId();
        if ("0".equals(status)) {
            //恢复，如果是暂停，可以执行激活操作 ,参数1 ：流程定义id ，参数2：是否激活，参数3：激活时间
            repositoryService.activateProcessDefinitionById(processId,
                    true,
                    null
            );
        }
        if ("1".equals(status)) {
            //挂起，如果是激活状态，可以暂停，参数1 ：流程定义id ，参数2：是否暂停，参数3：暂停时间
            repositoryService.suspendProcessDefinitionById(processId,
                    true,
                    null);
        }
        return Response.builder().code(0).build();
    }

}
