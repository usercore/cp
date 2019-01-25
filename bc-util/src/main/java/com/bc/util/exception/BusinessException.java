package com.bc.util.exception;


@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	private String msg;
	private String errno;

	public BusinessException(String errno, String message) {
		super(message);
		this.msg = message;
		this.errno = errno;
	}

	public BusinessException(String message) {
		super(message);
		this.msg = message;
		int pos = message.indexOf(":");
		this.errno = "111111";
		if (pos > -1) {
			try {
				errno = message.substring(0, pos).trim();
			} catch (Exception e) {
			}
		}
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
		this.errno = parseErrno(msg);
	}

	public BusinessException(String msg, String errno, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
		this.errno = errno;
	}

	public String getMessage() {
		return this.msg;
	}

	public String getErrno() {
		return this.errno;
	}
	
	protected String parseErrno(String message){
		 String errno = "111111";
		 int pos = message.indexOf(":");
		 if(pos >-1){
			 try{
		        errno = message.substring(0,pos).trim();
			 }
			 catch(Exception e){}
		 }
	   return errno;
	 }

}