package com.qbb.capture.enums;

public enum LotteryTypeEnum {

	SSQ("201", "双色球", "\\d{2}/\\d{2}/\\d{2}/\\d{2}/\\d{2}/\\d{2}//\\d{2}"),
	DLT("110", "大乐透", ""),
	D3("202", "3D", "\\d/\\d/\\d"),
	PL3("102", "排列3", ""),
	GANSU_K3("", "甘肃快3", "");
	
	private String code;
	
	private String name;
	
	private String regex;
	
	LotteryTypeEnum(String code, String name, String regex){
		this.code = code;
		this.name = name;
		this.regex = regex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
