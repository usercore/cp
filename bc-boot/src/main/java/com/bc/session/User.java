package com.bc.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4869013910209318387L;
	private Long id;
	private	String userName;
	private String email;
	private String password;
	private int enable ;
	private Date lastTime;
	private String lastIP;
	private BigDecimal balances;
	private String isVip;
	private String phone;
	private String code;
	private String mycode;
	/**
	 * 体验用户状态
	 * -1:不存在此体验用户
	 * -2：此体验账户不可用
	 * -3：没 激活
	 * -4：无效
	 * -5：过期
	 */
	private String expUserStatus;

	
	
	
	
	
	public String getExpUserStatus() {
		return expUserStatus;
	}
	public void setExpUserStatus(String expUserStatus) {
		this.expUserStatus = expUserStatus;
	}
	public String getMycode() {
		return mycode;
	}
	public void setMycode(String mycode) {
		this.mycode = mycode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public BigDecimal getBalances() {
		return balances;
	}
	public void setBalances(BigDecimal balances) {
		this.balances = balances;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
