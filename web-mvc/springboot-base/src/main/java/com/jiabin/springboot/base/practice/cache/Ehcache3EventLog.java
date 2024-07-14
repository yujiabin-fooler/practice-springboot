package com.jiabin.springboot.base.practice.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * @Description: Ehcache3 事件日志
 * @Author jiabin.yu
 * @Date: 2019/3/16
 */
@Slf4j
public class Ehcache3EventLog implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        log.info("Event: {},  Key: {}, old value: {}, new value: ",event.getType(),event.getKey(),
                event.getOldValue(),event.getNewValue());
    }


}
