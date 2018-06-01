package com.qbb.scheduler.model;

public class JobState implements java.io.Serializable {

	private static final long serialVersionUID = -4453664961096141902L;

	private long id;
	private String state;
	private String stateName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
