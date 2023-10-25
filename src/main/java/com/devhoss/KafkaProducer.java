package com.devhoss;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.devhoss.model.Transaccion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@Component
@EnableScheduling
public class KafkaProducer  {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedRate = 1500)
	public void SendMessages() throws JsonProcessingException {
		log.info("Sending messages ");
		Faker faker = new Faker();
		
		for(int i= 0;i< 1000;i++) {
			Transaccion transaccion = new Transaccion();
			transaccion.setUsername(faker.name().username());
			transaccion.setApellido(faker.name().lastName());
			transaccion.setNombre(faker.name().firstName());
			transaccion.setMonto(faker.number().randomDouble(4, 0, 20000));
			
			kafkaTemplate.send("testtopicreplication3",transaccion.getUsername(),objectMapper.writeValueAsString(transaccion));
		}
	}


}
