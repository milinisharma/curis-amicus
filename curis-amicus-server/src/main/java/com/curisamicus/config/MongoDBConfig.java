package com.curisamicus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


@Configuration
public class MongoDBConfig {
	
	@Value("${spring.data.mongodb.uri}")
	private String connectionString;
	
	@Bean
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(connectionString);
		MongoClient mongoClient = new MongoClient(uri);
		return mongoClient;
	}

}
