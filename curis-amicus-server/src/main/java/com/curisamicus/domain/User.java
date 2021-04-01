package com.curisamicus.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
@Document(collection = "ca-user")
public class User {
	
	@Id
	private String id;
	
	@Getter @Setter @Indexed
	private String username;
	
	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String firstName;
	
	@Getter @Setter
	private String middleName;
	
	@Getter @Setter
	private String lastName;
	
	@Getter @Setter
	private Date dob;
	
	@Getter @Setter
	private String gender;
	
	@Getter @Setter @DBRef(lazy = true)
	private Address address;
	
	@Getter @Setter @Indexed
	private String email;
	
	@Getter @Setter 
	private Long phone;
	
	@Getter @Setter
	private Integer age;
}
