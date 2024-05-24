package com.jiabin.activiti.practice.bo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author shiva   2022/3/16 17:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteTaskDTO implements Serializable {
    /**
     * 业务key 一般为业务主键 建议用uuid 保证唯一
     */
    @NotNull
    private String businessKey;
    /**
     * 流程类型
     */
    @NotNull
    private Integer processType;
    /**
     * 流程变量
     */
    private Map<String, Object> variables;
    /**
     * 同意/拒绝
     */
    @NotNull
    private Integer isAgree;
    /**
     * 备注，审批留言
     */
    private String comment;
    /**
     * 审批人
     */
    private String assignee;
}

