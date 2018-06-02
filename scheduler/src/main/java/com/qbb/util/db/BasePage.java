package com.qbb.util.db;

import java.io.Serializable;

public class BasePage implements Serializable{
	protected int pageNo;
	protected int pageSize;
	protected int startRecord = -1;
	
	protected String orderBy;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRecord() {
		if(pageSize!=0){
			return (pageNo-1)*pageSize;
		}
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
