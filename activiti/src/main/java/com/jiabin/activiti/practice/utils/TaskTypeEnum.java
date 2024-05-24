package com.jiabin.activiti.practice.utils;

import java.util.HashMap;
import java.util.Map;

public enum TaskTypeEnum {

    /**
     *
     */
    TODO_TASK(1,"我要处理-待办任务"),
    COMPLETE_TASK(2,"我要处理-已办任务"),
    MY_UNFINISH_TASK(3,"我发起-未处理任务"),
    MY_FINISH_TASK(4,"我发起-已处理任务"),
    ALL_TASK(5,"所有任务");

    public Integer index;
    public String text;

    private static final Map<Integer, TaskTypeEnum> ENUM_MAP = new HashMap<>();

    static {
        for (TaskTypeEnum item : TaskTypeEnum.values()) {
            ENUM_MAP.put(item.index, item);
        }
    }

    TaskTypeEnum(Integer index, String text){
        this.index = index;
        this.text = text;
    }

    public static String getTextByIndex(int index){
        final TaskTypeEnum enumValue = ENUM_MAP.get(index);
        return enumValue == null ? null : enumValue.text;
    }

    public static TaskTypeEnum resolve(int index){
        return ENUM_MAP.get(index);
    }

}
