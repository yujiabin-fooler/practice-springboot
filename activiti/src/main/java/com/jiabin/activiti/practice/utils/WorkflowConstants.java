package com.jiabin.activiti.practice.utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiva   2022/3/16 12:40
 */
public final class WorkflowConstants {


    public static final Map<String, String> ROLE_TABLE = new HashMap<>();

    /*
    |角色 |角色英文名|角色编号|
    |总经理 |generalManager|088|
    */
    static {
        ROLE_TABLE.put("groupManager", "groupManagerList");
        ROLE_TABLE.put("projectManager", "projectManagerList");
        ROLE_TABLE.put("companyManager", "companyManagerList");
        ROLE_TABLE.put("admin", "adminList");
    }

    /**
     * 动态流程图颜色定义
     **/
    public static final Color COLOR_NORMAL = new Color(0, 205, 0);

    public static final Color COLOR_CURRENT = new Color(255, 0, 0);

    /**
     * 定义生成流程图时的边距(像素)
     **/
    public static final int PROCESS_PADDING = 5;

    /**
     * 审批流程变量(必填) - 申请人
     */
    public static final String VARIABLE_APPLY_USER = "applyUser";
    /**
     * 审批流程变量(必填)  - 审批业务类型
     */
    public static final String VARIABLE_PROCESS_TYPE = "processType";
    /**
     * 审批流程变量(必填)  - 审批是否同意 必传项 值为0或1
     */
    public static final String VARIABLE_IS_AGREE = "isAgree";
    /**
     * 审批流程变量(必填)  - 业务回调URL
     */
    public static final String VARIABLE_CALLBACK_URL = "callbackUrl";
    /**
     * 审批流程变量(必填) - 业务id
     */
    public static final String VARIABLE_BUSINESS_KEY = "businessKey";
    /**
     * 审批流程变量(必填) - 业务编号
     */
    public static final String VARIABLE_BILL_NO = "relationBusNo";
    /**
     * 审批流程变量(必填) - 申请原因
     */
    public static final String VARIABLE_APPLY_REASON = "applyReason";

    /**
     * 跳过节点配置
     */
    public static final String SKIP_EXPRESSION = "_ACTIVITI_SKIP_EXPRESSION_ENABLED";


    /**
     * 同意
     */
    public static final int AGREE = 1;
    /**
     * 拒绝
     */
    public static final int REFUSE = 0;


    /**
     * 同意操作 显示信息
     */
    public static final String COMMENT_AGREE = "【同意】";
    /**
     * 不同意操作 显示信息
     */
    public static final String COMMENT_REFUSE = "【拒绝】";
    /**
     * 批注默认 显示信息
     */
    public static final String COMMENT_DEFAULT = "【无批注信息】";


}
