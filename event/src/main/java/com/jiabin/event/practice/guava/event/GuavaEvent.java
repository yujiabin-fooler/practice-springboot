package com.jiabin.event.practice.guava.event;

import lombok.Data;

//import java.io.Serial;
import java.io.Serializable;

/**
 * Guava 实现事件对象
 */
@Data
public class GuavaEvent implements Serializable {

//    @Serial
    private static final long serialVersionUID = 1L;

    private String data;

    public GuavaEvent(String data) {
        this.data = data;
    }
}
