package org.paralun;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.paralun.config.KafkaConfig;
import org.paralun.serialization.Person;
import org.paralun.serialization.PersonSerializer;

import java.util.Properties;

public class PersonProducerApp {
    public static void main(String[] args) {
        Properties props = KafkaConfig.getProducerConfig();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, PersonSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PersonSerializer.class.getName());

        KafkaProducer<String, Person> producer = new KafkaProducer<String, Person>(props);
        for (int i = 1; i <= 10; i++) {
            Person person = new Person();
            person.setId("ID" + i);
            person.setName("Nama" + i);

            ProducerRecord<String, Person> record = new ProducerRecord<>(KafkaConfig.TOPIC_NAME, person);
            producer.send(record);
        }

        producer.close();
    }
}
