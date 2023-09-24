package com.dev.torhugo.hub_payments.lib.exception.impl;

import lombok.Getter;

@Getter
public class DataBaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final Object error;

	public DataBaseException(final String msg, final Object object) {
		super(msg);
		this.error = object;
	}
}
