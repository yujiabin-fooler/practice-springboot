package com.jiabin.mysql.binlog.practice.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.jmx.BinaryLogClientStatistics;

public class JMXTest {

  public static void main(String[] args) throws Exception {
    MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

    BinaryLogClient client = new BinaryLogClient("118.24.111.33", 3307, "test", "root", "123123");
    ObjectName objectName = new ObjectName("mysql.binlog:type=BinaryLogClient");
    mBeanServer.registerMBean(client, objectName);

    // following bean accumulates various BinaryLogClient stats
    // (e.g. number of disconnects, skipped events)
    BinaryLogClientStatistics stats = new BinaryLogClientStatistics(client);
    ObjectName statsObjectName = new ObjectName("mysql.binlog:type=BinaryLogClientStatistics");
    mBeanServer.registerMBean(stats, statsObjectName);
    
    System.in.read() ;
  }
  
}
