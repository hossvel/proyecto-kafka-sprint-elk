package com.devhoss.kafka.elk;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerConfiguration {

	@Bean
	public Map<String, Object> consumerProps() { 

		Map<String, Object>props=new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"devhoss-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,	IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);

		//props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,	true);
		//props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"100");
		//props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,	"15000");
		return props;
	}

	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
		return new 	DefaultKafkaConsumerFactory<>(consumerProps());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String>
		factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setBatchListener(true);
		factory.setConcurrency(5);// 3 hilos
			

		return factory;
	}
}