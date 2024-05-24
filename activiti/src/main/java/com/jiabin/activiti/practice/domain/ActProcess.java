package com.jiabin.activiti.practice.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shiva   2022/3/13 22:40
 */
@Data
public class ActProcess implements Serializable {
    private String id;
    private String instanceId;
    private String processNo;
    private Integer processType;
    private String processName;
    private String relationBusNo;
    private String businessKey;
    private String applyUser;
    private Date startTime;
    private Date endTime;
    private Integer isAgree;
    private Integer status;
    private String applyReason;
    private String finalAssignee;
    private String actualAssigneeList;
    private String currentAssignee;
    private String currentTask;
    private Date acceptTaskTime;
    private String callBackUrl;
    private Integer isDelete;
    private String remarks;

    /**
     * 1.待处理任务  2.已办理任务  3.我发起的处理中的任务  4.我发起的处理完成的任务
     */
    @TableField(exist = false)
    private Integer taskType;

}
