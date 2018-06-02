package com.qbb.util.exception;


@SuppressWarnings("serial")
public class MijiaBusinessException extends RuntimeException {
	private String msg;
	private String errno;

	public MijiaBusinessException(String errno, String message) {
		super(message);
		this.msg = message;
		this.errno = errno;
	}

	public MijiaBusinessException(String message) {
		super(message);
		this.msg = message;
		int pos = message.indexOf(":");
		this.errno = "-1024";
		if (pos > -1) {
			try {
				errno = message.substring(0, pos).trim();
			} catch (Exception e) {
			}
		}
	}

	public MijiaBusinessException(String msg, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
		this.errno = parseErrno(msg);
	}

	public MijiaBusinessException(String msg, String errno, Throwable cause) {
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
		 String errno = "-1024";
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