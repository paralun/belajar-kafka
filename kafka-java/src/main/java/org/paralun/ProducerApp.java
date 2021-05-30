package org.paralun;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.paralun.config.KafkaConfig;

public class ProducerApp {
    public static void main(String[] args) {

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(KafkaConfig.getProducerConfig());
        for (int i = 1; i <= 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConfig.TOPIC_NAME, "Data ke " + i);
            producer.send(record);
        }

        producer.close();
    }
}
