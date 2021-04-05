package com.curisamicus.domain.payload.request;

import com.curisamicus.domain.User;

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
public class RegisterRequest {
	
	@Getter @Setter
	private User user;

}
