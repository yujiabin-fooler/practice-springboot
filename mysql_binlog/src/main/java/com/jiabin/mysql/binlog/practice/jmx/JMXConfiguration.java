package com.jiabin.mysql.binlog.practice.jmx;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.jmx.BinaryLogClientStatistics;

@Configuration
public class JMXConfiguration {

  @Bean
  MBeanExporter exporterClient(BinaryLogClient client) {
    MBeanExporter exporter = new MBeanExporter();
    exporter.setBeans(Map.of("mysql.binlog:type=BinaryLogClient", client)) ;
    return exporter;
  }
  
  @Bean
  MBeanExporter exporterClientStatistics(BinaryLogClient client) {
    MBeanExporter exporter = new MBeanExporter();
    BinaryLogClientStatistics stats = new BinaryLogClientStatistics(client);
    exporter.setBeans(Map.of("mysql.binlog:type=BinaryLogClientStatistics", stats)) ;
    return exporter;
  }
}
