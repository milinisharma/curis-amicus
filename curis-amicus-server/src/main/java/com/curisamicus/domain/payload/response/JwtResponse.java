package com.curisamicus.domain.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
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
public class JwtResponse {
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter @Builder.Default
	private String type = "Bearer";
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private List<String> roles;

}
