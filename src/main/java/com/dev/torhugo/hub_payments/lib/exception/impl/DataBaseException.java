package com.dev.torhugo.hub_payments.lib.exception.impl;

import lombok.Getter;

@Getter
public class DataBaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final Object error;
	private final String href;
	private final String method;

	public DataBaseException(final String msg,
							 final Object object,
							 final String href,
							 final String method) {
		super(msg);
		this.error = object;
		this.href = href;
		this.method = method;
	}
}
