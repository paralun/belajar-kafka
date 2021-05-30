package org.paralun.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConfig {

    public static String TOPIC_NAME = "test";

    public static Properties getProducerConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    public static Properties getConsumerConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }

    public void createTopic() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(AdminClientConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 10000);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 5000);

        String newTopic = "topic1";
        int numPartitions = 1;
        short replication = (short) 1;
        try (AdminClient client = AdminClient.create(props)){
            CreateTopicsResult result = client.createTopics(Arrays.asList(
                    new NewTopic("topic1", numPartitions, replication)));
            result.values().get(newTopic).get();
            client.close();
        }catch (Exception e) {

        }
    }

    public void deleteTopic() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(AdminClientConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 10000);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 5000);

        String newTopic = "topic1";
        try (AdminClient client = AdminClient.create(props)){
            client.deleteTopics(Arrays.asList(newTopic));
        }catch (Exception e) {

        }
    }
}
