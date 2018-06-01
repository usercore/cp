package com.qbb.jobs.pub.exception;

/**
 * 参数错误异常
 * 
 *
 */
public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = 8530224912978751003L;

	public InvalidArgumentException() {
		super();
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

}
