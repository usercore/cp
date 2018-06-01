package com.qbb.util.calculator.finance.math.exception;

/**
 * 参数错误异常
 */
public class ProcessException extends Exception {

	private static final long serialVersionUID = 8530224912978751003L;
	private String message;

	public ProcessException() {
		super();
	}

	public ProcessException(String message) {
		super();
		this.message = message;
	}

	public ProcessException(String message, Throwable e) {
		super(e);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
