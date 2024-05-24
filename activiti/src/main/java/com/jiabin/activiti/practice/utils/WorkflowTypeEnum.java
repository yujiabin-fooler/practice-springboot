package com.jiabin.activiti.practice.utils;

/**
 * @author shiva   2022/3/15 23:04
 */
public enum WorkflowTypeEnum {

    /**
     *
     */
    ASK_LEAF(10, "请假", "AskLeaf", "groupManager", ""),
    RESIGN(20, "离职", "Resign", "projectManager,companyManager", "com.xxx.xx.x.ss()")
    ;

    public final Integer value;
    public final String name;
    public final String key;
    public final String roles;
    public final String callbackUrl;

    WorkflowTypeEnum(Integer value, String name, String key, String roles, String callbackUrl) {
        this.value = value;
        this.name = name;
        this.key = key;
        this.roles = roles;
        this.callbackUrl = callbackUrl;
    }

    /**
     * 根据流程类型，获得流程类型实例
     */
    public static WorkflowTypeEnum resolve(Integer value) {
        if (value == null) {
            return null;
        }
        for (WorkflowTypeEnum shiftTypeEnum : values()) {
            if (shiftTypeEnum.value.equals(value)) {
                return shiftTypeEnum;
            }
        }
        return null;
    }

}
