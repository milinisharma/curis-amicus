package com.curisamicus.domain.auth;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@EqualsAndHashCode
@Document(collection = "ca-user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -5564927731467854058L;
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
	
	public User() {
		super();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
