package com.devhoss;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics ="testtopicreplication3", groupId ="devhoss-group", containerFactory ="kafkaListenerContainerFactory")
	public void listen(List<ConsumerRecord<Integer, String>>messages) {
		
		System.out.println("Inicio Batch...");
		for (ConsumerRecord<Integer, String> message : messages) {
			log.info("Offset {} Partition= {} Key = {} Value = {} ", message.offset(),message.partition(), message.key(),message.value());
		}
		System.out.println("Completado bash...");
		
	}
}
