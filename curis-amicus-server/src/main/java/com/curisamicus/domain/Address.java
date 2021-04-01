package com.curisamicus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode
@ToString
@Document(collection = "ca-user-address")
public class Address {
	
	@Id
	private String id;
	
	@Getter @Setter
	private String addressLine1;
	
	@Getter @Setter
	private String addressLine2;
	
	@Getter @Setter
	private String addressLine3;
	
	@Getter @Setter
	private String city;
	
	@Getter @Setter
	private String state;
	
	@Getter @Setter
	private String country;
	
	@Getter @Setter
	private String pincode;

}
