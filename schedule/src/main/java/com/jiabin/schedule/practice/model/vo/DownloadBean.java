package com.jiabin.schedule.practice.model.vo;

import com.jiabin.schedule.practice.model.BaseBean;
import lombok.Data;

/**
 * @Description: 下载
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@Data
public class DownloadBean extends BaseBean {

    /**
     * 待下载文件名
     */
    private String fileName;
}
