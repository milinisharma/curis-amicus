package com.curisamicus.domain.payload.response;

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
public class MessageResponse {

	@Getter @Setter
	private String message;
	
}
