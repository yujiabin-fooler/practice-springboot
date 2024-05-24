package com.jiabin.erupt.job.practice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.erupt.core.annotation.EruptScan;

/**
 * Created by macro on 2021/4/13.
 */
@Configuration
@ComponentScan({"xyz.erupt","com.jiabin.erupt.job.practice"})
@EntityScan({"xyz.erupt","com.jiabin.erupt.job.practice"})
@EruptScan({"xyz.erupt","com.jiabin.erupt.job.practice"})
public class EruptConfig {
}
