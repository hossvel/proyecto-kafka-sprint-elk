package com.devhoss;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements CommandLineRunner {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaProducer.class);


	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("ENVIANDOO...");
		for (int i = 0;i < 100;i++) {
			kafkaTemplate.send("testtopicreplication3",Integer.valueOf(i),"Mensaje BATCH - " + (i + 1));
		}
	}
}
