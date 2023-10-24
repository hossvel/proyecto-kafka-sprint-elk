package com.devhoss;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class KafkaProducer  {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaProducer.class);


	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	@Scheduled(fixedDelay = 2000, initialDelay = 100)
	public void SendMessages() {
		log.info("Sending messages ");
		for(int i= 0;i< 200;i++) {
			kafkaTemplate.send("testtopicreplication3",Integer.valueOf(i),"Mensaje REPETIDO CADA 2 seg - " + (i + 1));
		}
	}


}
