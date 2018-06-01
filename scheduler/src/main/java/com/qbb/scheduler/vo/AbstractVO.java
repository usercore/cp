package com.qbb.scheduler.vo;

import java.io.Serializable;

public abstract class AbstractVO implements Serializable{

	private static final long serialVersionUID = 1663456079534866794L;
	
	// 成功/失败
	boolean success = true;
	// 状态码
	int code = 0;
	// 消息体
	String message = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
