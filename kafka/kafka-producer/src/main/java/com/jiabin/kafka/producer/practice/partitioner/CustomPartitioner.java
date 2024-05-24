package com.jiabin.kafka.producer.practice.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * myKafkaTest
 * 20211023
 * 自定义分区器
 */
public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer integer = cluster.partitionCountForTopic(topic);
        if (null == integer) {
            return Utils.toPositive(ThreadLocalRandom.current().nextInt()) % integer;
        } else {
            return Utils.toPositive(Utils.murmur2(keyBytes)) % integer;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
