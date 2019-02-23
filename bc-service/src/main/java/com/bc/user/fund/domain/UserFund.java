
package com.bc.user.fund.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.bc.util.db.BasePage;

/**
 * user_fund
 * 
 * 
 */
public class UserFund extends BasePage implements Serializable {
	private static final long serialVersionUID = "UserFund".hashCode();
	
	private Long id;//
	private String userId;//
	private BigDecimal userTotal;//
	private BigDecimal userUsable;//
	private BigDecimal userFreeze;//
	private BigDecimal userInvest;//
	private BigDecimal userAward;//
	private BigDecimal userInterest;//
	private Date createTime;//
	private Long updateTime;//
	
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
	public BigDecimal getUserTotal() {
		return this.userTotal;
	}
	public void setUserTotal(BigDecimal userTotal) {
		this.userTotal = userTotal;
	}
	public BigDecimal getUserUsable() {
		return this.userUsable;
	}
	public void setUserUsable(BigDecimal userUsable) {
		this.userUsable = userUsable;
	}
	public BigDecimal getUserFreeze() {
		return this.userFreeze;
	}
	public void setUserFreeze(BigDecimal userFreeze) {
		this.userFreeze = userFreeze;
	}
	public BigDecimal getUserInvest() {
		return this.userInvest;
	}
	public void setUserInvest(BigDecimal userInvest) {
		this.userInvest = userInvest;
	}
	public BigDecimal getUserAward() {
		return this.userAward;
	}
	public void setUserAward(BigDecimal userAward) {
		this.userAward = userAward;
	}
	public BigDecimal getUserInterest() {
		return this.userInterest;
	}
	public void setUserInterest(BigDecimal userInterest) {
		this.userInterest = userInterest;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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
		if (!(other instanceof UserFund))
			return false;

		UserFund castOther = (UserFund) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("userId:").append(userId.toString()).append(",");
		builder.append("userTotal:").append(userTotal.toString()).append(",");
		builder.append("userUsable:").append(userUsable.toString()).append(",");
		builder.append("userFreeze:").append(userFreeze.toString()).append(",");
		builder.append("userInvest:").append(userInvest.toString()).append(",");
		builder.append("userAward:").append(userAward.toString()).append(",");
		builder.append("userInterest:").append(userInterest.toString()).append(",");
		builder.append("createTime:").append(createTime.toString()).append(",");
		builder.append("updateTime:").append(updateTime.toString());
		return builder.toString();
	}
}
