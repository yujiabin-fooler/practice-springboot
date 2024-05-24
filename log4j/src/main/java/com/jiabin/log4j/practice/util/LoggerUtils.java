package com.jiabin.log4j.practice.util;

import org.apache.log4j.Logger;

/**
 * Logger 工具类
 *
 */
public class LoggerUtils {

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("SpringBootDemoLogs");
        }
        return logger;
    }
}