package com.jiabin.activiti.practice.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shiva   2022/3/13 21:51
 */
@Data
public class EmployeeBO implements Serializable {
    private String loginAs;
    private String reason;
    private String leafTime;
}
