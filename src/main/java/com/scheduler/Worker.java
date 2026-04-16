package com.scheduler;

import org.apache.kafka.clients.consumer.*;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Worker {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "workers");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("jobs"));

        Jedis redis = new Jedis("localhost", 6379);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                String job = record.value();
                System.out.println("Processing: " + job);

                try {
                    // simulate processing
                    Thread.sleep(500);
                    redis.set(job, "COMPLETED");
                } catch (Exception e) {
                    redis.set(job, "FAILED");
                }
            }
        }
    }
}
