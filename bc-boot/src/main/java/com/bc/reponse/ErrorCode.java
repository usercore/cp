package com.bc.reponse;

public enum ErrorCode {
	userNotLogin("700002","您还未登陆"),paraIsToLong("700001","输入参数过长，最大长度为");
	
	
	private String errorCode;
	private String errorMessage;
	private  ErrorCode(String errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
