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
@ToString
@EqualsAndHashCode
@Document(collection = "ca-roles")
public class Role {
	
	@Id
	private Integer id;
	
	@Getter @Setter
	private ERole name;

}
