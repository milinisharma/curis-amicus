package com.curisamicus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ca_db_seq")
public class DatabaseSequence {
	
	 @Id
	 private String id;
	 
	 private int seq;

}
