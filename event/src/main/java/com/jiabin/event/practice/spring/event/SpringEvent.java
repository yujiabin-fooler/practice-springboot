package com.jiabin.event.practice.spring.event;

import lombok.Data;

//import java.io.Serial;
import java.io.Serializable;

/**
 * Spring 事件对象
 */
@Data
public class SpringEvent implements Serializable {

//    @Serial
    private static final long serialVersionUID = 1L;

    private String data;

    public SpringEvent(String data) {
        this.data = data;
    }
}
