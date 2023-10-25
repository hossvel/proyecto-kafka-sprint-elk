package com.devhoss.config;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.devhoss.ProyectoKafkaSprintElkApplication;

@Component
public class ElastickConfig {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(ElastickConfig.class);
	
		
		 @Bean(destroyMethod = "close")
		    public RestHighLevelClient client() {


				RestHighLevelClient client = new RestHighLevelClient(
		                RestClient.builder(
		                        new HttpHost("localhost", 9200, "http")));

		        return client;

		    }

		
	
}