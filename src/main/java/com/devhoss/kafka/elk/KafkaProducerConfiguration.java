package com.devhoss.kafka.elk;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerConfiguration {


	@Bean
	public Map<String, Object> producerProps() { 
		Map<String, Object> props=new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,	"localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,	StringSerializer.class);

		//props.put(ProducerConfig.RETRIES_CONFIG, 0);
		//props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		//props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		//props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);


		return props;
	}


	@Bean
	public KafkaTemplate<Integer, String> createTemplate() {
		Map<String, Object>senderProps= producerProps();
		ProducerFactory<Integer, String> pf= new DefaultKafkaProducerFactory<Integer, String>(senderProps);
		KafkaTemplate<Integer, String> template=new KafkaTemplate<>(pf);
		return template;
	}


}
