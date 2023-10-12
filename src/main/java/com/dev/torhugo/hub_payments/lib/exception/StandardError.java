package com.dev.torhugo.hub_payments.lib.exception;

import com.dev.torhugo.hub_payments.lib.data.dto.LinkResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	@JsonProperty("_links")
	private LinkResponseDTO link;
}
