package org.paralun;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.paralun.config.KafkaConfig;

import java.time.Duration;
import java.util.Arrays;

public class ConsumerApp {
    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaConfig.getConsumerConfig());
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received Data: " + record.value());
            }
        }
    }
}
