
package com.bc.user.info.domain;

import java.io.Serializable;

import java.util.Date;
import com.bc.util.db.BasePage;

/**
 * user_info
 * 
 * 
 */
public class UserInfo extends BasePage implements Serializable {
	private static final long serialVersionUID = "UserInfo".hashCode();
	public static final String PWD_PRE = "bc@#*&^!bc";
	
	private Long id;//
	private String userId;//
	private String userType;//
	private String userName;//
	private String userRename;//
	private Date registerTime;//
	private String registerIp;//
	private String headPath;//
	private String loginPwd;//
	private String tranPwd;//
	private String userPhone;//
	private String enable;//
	private String channel;//
	private Integer pwrtCount;//
	private Date locktime;//
	private Date lastLoginTime;//
	private Date loginIme;//
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return this.userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRename() {
		return this.userRename;
	}
	public void setUserRename(String userRename) {
		this.userRename = userRename;
	}
	public Date getRegisterTime() {
		return this.registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getRegisterIp() {
		return this.registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getHeadPath() {
		return this.headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	public String getLoginPwd() {
		return this.loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getTranPwd() {
		return this.tranPwd;
	}
	public void setTranPwd(String tranPwd) {
		this.tranPwd = tranPwd;
	}
	public String getUserPhone() {
		return this.userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getEnable() {
		return this.enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getChannel() {
		return this.channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getPwrtCount() {
		return this.pwrtCount;
	}
	public void setPwrtCount(Integer pwrtCount) {
		this.pwrtCount = pwrtCount;
	}
	public Date getLocktime() {
		return this.locktime;
	}
	public void setLocktime(Date locktime) {
		this.locktime = locktime;
	}
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLoginIme() {
		return this.loginIme;
	}
	public void setLoginIme(Date loginIme) {
		this.loginIme = loginIme;
	}
	public int hashCode() {
		int result = 17;
		result = 37 * result + (this.id == null ? 0 : this.id.hashCode());

		return result;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserInfo))
			return false;

		UserInfo castOther = (UserInfo) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("userId:").append(userId.toString()).append(",");
		builder.append("userType:").append(userType.toString()).append(",");
		builder.append("userName:").append(userName.toString()).append(",");
		builder.append("userRename:").append(userRename.toString()).append(",");
		builder.append("registerTime:").append(registerTime.toString()).append(",");
		builder.append("registerIp:").append(registerIp.toString()).append(",");
		builder.append("headPath:").append(headPath.toString()).append(",");
		builder.append("loginPwd:").append(loginPwd.toString()).append(",");
		builder.append("tranPwd:").append(tranPwd.toString()).append(",");
		builder.append("userPhone:").append(userPhone.toString()).append(",");
		builder.append("enable:").append(enable.toString()).append(",");
		builder.append("channel:").append(channel.toString()).append(",");
		builder.append("pwrtCount:").append(pwrtCount.toString()).append(",");
		builder.append("locktime:").append(locktime.toString()).append(",");
		builder.append("lastLoginTime:").append(lastLoginTime.toString()).append(",");
		builder.append("loginIme:").append(loginIme.toString());
		return builder.toString();
	}
}
