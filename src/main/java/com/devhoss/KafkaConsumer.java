package com.devhoss;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	//@KafkaListener(topics ="testtopicreplication3", groupId ="devhoss-group")

    // bloques de  10 registros dentro de los 4 segundos
	@KafkaListener(topics ="testtopicreplication3",containerFactory ="kafkaListenerContainerFactory",groupId ="devhoss-group", properties =
		{"max.poll.interval.ms:4000","max.poll.records:10"})
	public void listen(List<ConsumerRecord<Integer, String>>messages) {
		
		System.out.println("Inicio Batch...");
		for (ConsumerRecord<Integer, String> message : messages) {
			log.info("Offset {} Partition= {} Key = {} Value = {}", message.offset(),message.partition(), message.key(),message.value());
		}
		System.out.println("Completado bash...");
		
	}
}
