package org.paralun;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.paralun.config.KafkaConfig;
import org.paralun.serialization.Person;
import org.paralun.serialization.PersonDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class PersonConsumerApp {
    public static void main(String[] args) {
        Properties props = KafkaConfig.getConsumerConfig();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, PersonDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PersonDeserializer.class.getName());

        KafkaConsumer<String, Person> consumer = new KafkaConsumer<String, Person>(props);
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, Person> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, Person> record : records) {
                System.out.println("ID: " + record.value().getId() + " Name: " + record.value().getName());
            }
        }
    }
}
