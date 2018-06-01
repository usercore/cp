package com.qbb.util.calculator.finance.math.exception;

/**
 * 参数错误异常
 * 
 */
public class DataException extends Exception {

	private static final long serialVersionUID = 8530224912978751003L;
	private String message;

	public DataException() {
		super();
	}

	public DataException(String message) {
		super();
		this.message = message;
	}

	public DataException(String message, Throwable e) {
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
