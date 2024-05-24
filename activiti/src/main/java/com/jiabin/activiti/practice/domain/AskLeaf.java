package com.jiabin.activiti.practice.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shiva   2022/3/13 22:01
 */
@Data
public class AskLeaf implements Serializable {
    //申请人
    private String applicant;
    //申请原因
    private String reason;
    //申请时间
    private Date applyTime;
    //请假时间，日期
    private Date leafTime;
    //审批时间
    private Date approvalTime;
    //最终审批人
    private String finalApprover;
    //审批状态; 0-审批中，1-通过，2-未通过
    private String status;
    //审批备注
    private String remarks;
}
