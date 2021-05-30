package com.paralun.kafka;

import com.paralun.kafka.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaSpringbootApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSpringbootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringbootApplication.class, args);
	}

	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void recieveData(Person person) {
		LOGGER.info("Id: " + person.getId() + " Name: " + person.getName());
	}

}
