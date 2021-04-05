package com.curisamicus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@Getter @Setter @Indexed @NotBlank @Size(max = 20)
	private String username;
	
	@Getter @Setter @NotBlank @Size(max = 120)
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
	
	@Getter @Setter @Indexed @NotBlank @Size(max = 50) @Email
	private String email;
	
	@Getter @Setter 
	private Long phone;
	
	@Getter @Setter
	private Integer age;
	
	@DBRef @Builder.Default
	private Set<Role> roles = new HashSet<>();
}
