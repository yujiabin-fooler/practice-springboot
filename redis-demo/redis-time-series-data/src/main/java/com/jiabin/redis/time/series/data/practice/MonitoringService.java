package com.jiabin.redis.time.series.data.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private LettuceConnectionFactory connectionFactory;

    public void logCpuUsage(String serverId, double cpuUsage) {
//        // 记录CPU使用率数据
//        CpuUsageData data = new CpuUsageData(Instant.now(), cpuUsage);
//        // 使用Lettuce客户端的命令执行器来与RedisTimeSeries模块交互
//        StatefulRedisConnection<String, CpuUsageData> connection = connectionFactory.connect();
//        try {
//            RedisTimeSeriesCommands<String, CpuUsageData> ts = connection.sync();
//            ts.add(serverId, data.getTimestamp().toEpochMilli() / 1000, data);
//        } finally {
//            connection.close();
//        }

    }

    public List<CpuUsageData> getCpuUsageHistory(String serverId, Instant start, Instant end) {
        // 查询指定时间范围内的CPU使用率历史数据
//        List<CpuUsageData> history = new ArrayList<>();
//        StatefulRedisConnection<String, CpuUsageData> connection = connectionFactory.connect();
//        try {
//            RedisTimeSeriesCommands<String, CpuUsageData> ts = connection.sync();
//            Range range = Range.create(start.toEpochMilli() / 1000, end.toEpochMilli() / 1000);
//            Cursor<CpuUsageData> cursor = ts.rangeRead(serverId, range);
//            while (cursor.hasNext()) {
//                history.add(cursor.next().getValue());
//            }
//        } finally {
//            connection.close();
//        }
//        return history;
        return null;
    }
}
