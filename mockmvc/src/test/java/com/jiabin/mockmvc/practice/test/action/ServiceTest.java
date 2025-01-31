package com.jiabin.mockmvc.practice.test.action;

import com.jiabin.mockmvc.practice.mbg.model.PmsBrand;
import com.jiabin.mockmvc.practice.service.PmsBrandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Service层方法测试
 * @author jiabin.yu 2022/10/11.
 */
@SpringBootTest
public class ServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);
    @Autowired
    private PmsBrandService brandService;

    @Test
    void testGetById(){
        long id = 6;
        PmsBrand pmsBrand = brandService.getBrand(id);
        LOGGER.info("brand name:{}",pmsBrand.getName());
        Assertions.assertEquals("小米",pmsBrand.getName());
    }
}
