package com.curisamicus.domain;

import org.springframework.data.annotation.Transient;

import lombok.Data;

@Data
public class GenericSequence {
	
	@Transient
    public static final String SEQUENCE_NAME = "generic_sequence";

}
