package com.devhoss;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	RestHighLevelClient client;


	@KafkaListener(topics ="testtopicreplication3", groupId ="devhoss-group", containerFactory ="kafkaListenerContainerFactory")
	public void listen(List<ConsumerRecord<Integer, String>>messages) {

		for (ConsumerRecord<Integer, String> message : messages) {
			IndexRequest request = buidIndexRequest(
					String.format("%s-%s-%s", message.offset(),message.partition(), message.key()),
					message.value());
			
			client.indexAsync(request, RequestOptions.DEFAULT,new ActionListener<IndexResponse>() {

				@Override
				public void onResponse(IndexResponse response) {
					// TODO Auto-generated method stub
					log.debug("Success request");
				}

				@Override
				public void onFailure(Exception e) {
					// TODO Auto-generated method stub
					log.error("Error store message");
				}


			});


		}
		
	}


	private IndexRequest buidIndexRequest(String key, String value) {

		IndexRequest request = new IndexRequest("devhoss-transactions");
		request.id(key);
		request.source(value,XContentType.JSON);
		return request;

	}

}
