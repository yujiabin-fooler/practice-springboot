package com.jiabin.geodesy.test;

import com.jiabin.geodesy.practice.GeodesyPracticeApplication;
import com.jiabin.geodesy.practice.util.GeodsyDistanceUtils;
import com.jiabin.geodesy.practice.util.MathDistanceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeodesyPracticeApplication.class)
public class DemoTests {
    private Logger log = LoggerFactory.getLogger(getClass());


    @Before
    public void before()  {
        log.info("init some data");
    }
    @After
    public void after(){
        log.info("clean some data");
    }
    @Test
    public void execute()  {
        log.info("your method test Code");
    }
    @Test
    public void getDistance() {
        // source （113.324553,23.106414）
        // target （121.499718, 31.239703）
        double distance1 = GeodsyDistanceUtils.getDistance(113.324553,23.106414,
                121.499718, 31.239703,2);
        System.out.println("distant1（m）：" + distance1);
        double distance2 = MathDistanceUtil.getDistance(113.324553, 23.106414, 121.499718, 31.239703);
        System.out.println("distant2（m）：" + distance2);
    }

}

