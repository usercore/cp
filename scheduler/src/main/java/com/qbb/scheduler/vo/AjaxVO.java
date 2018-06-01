package com.qbb.scheduler.vo;


public class AjaxVO extends AbstractVO {
	
	private static final long serialVersionUID = -2885416640027861649L;
	// 结果集对象
	private Object data = null;

	public static AjaxVO build(int code, String message, Object object) {
		AjaxVO dto = build();
		dto.setCode(code);
		dto.setMessage(message);
		dto.setData(object);
		return dto;
	}

	public static AjaxVO build(int code, String message) {
		AjaxVO dto = build();
		dto.setCode(code);
		dto.setMessage(message);
		return dto;
	}
	
	public static AjaxVO build(String message) {
		AjaxVO dto = build();
		dto.setMessage(message);
		return dto;
	}
	
	public static AjaxVO build(int code) {
		AjaxVO dto = build();
		dto.setCode(code);
		return dto;
	}

	public static AjaxVO build() {
		return new AjaxVO();
	}

	private AjaxVO() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public AjaxVO set(int code, String message, Object object) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(object);
		return this;
	}

	public AjaxVO set(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
		return this;
	}
	
	public AjaxVO set(int code, Object object) {
		this.setCode(code);
		this.setData(object);
		return this;
	}
	
	public AjaxVO set(String message, Object object) {
		this.setMessage(message);
		this.setData(object);
		return this;
	}
	
	public AjaxVO set(String message) {
		this.setMessage(message);
		return this;
	}
	
	public AjaxVO set(Object object) {
		this.setData(object);
		return this;
	}
	
	public AjaxVO set(int code) {
		this.setCode(code);
		return this;
	}
	
	@Override
	public String toString() {
		return "AjaxDTO [success=" + success + ", code=" + code + ", data=" + data + ", message=" + message + "]";
	}
	
}
