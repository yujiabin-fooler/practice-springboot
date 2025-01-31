package com.jiabin.global.formatting.practice.controller;

import com.alibaba.fastjson.JSON;
import com.jiabin.global.formatting.practice.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jiabin.yu
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping
public class GlobalFormattingTimeController {

    @RequestMapping("/timeTest")
    public OrderDTO timeTest(OrderDTO orderDTO) {
        OrderDTO dto = new OrderDTO();
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(new Date());

        log.info(JSON.toJSONString(dto));
        return dto;
    }
}
