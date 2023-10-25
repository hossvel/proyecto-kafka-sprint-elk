package com.devhoss;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProyectoKafkaSprintElkApplication //implements CommandLineRunner
{

	//public static final org.slf4j.Logger log = LoggerFactory.getLogger(ProyectoKafkaSprintElkApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoKafkaSprintElkApplication.class, args);
	}
	
	
	/*
	 * @Autowired RestHighLevelClient client;
	 * 
	 * public void run(String... args) throws Exception { log.info("Inicio");
	 * IndexRequest request= new IndexRequest("indexprueba"); request.id("3");
	 * request.source("{\"nombre\":\"Sammie\"," +"\"apellido\":\"Goldner\","
	 * +"\"username\":\"hugh.vonrueden\"," +"\"monto\":9622235.2009}",
	 * XContentType.JSON);
	 * 
	 * IndexResponse response = client.index(request, RequestOptions.DEFAULT);
	 * log.info("Response id = {}",response.getId()); }
	 */

}
