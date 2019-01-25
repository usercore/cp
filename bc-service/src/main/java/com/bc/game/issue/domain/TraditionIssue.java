
package com.bc.game.issue.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.bc.util.db.BasePage;

/**
 * tradition_issue
 * 
 * 
 */
public class TraditionIssue extends BasePage implements Serializable {
	private static final long serialVersionUID = "TraditionIssue".hashCode();
	
	private Integer id;//
	private String issueNo;//
	private Integer status;//
	private Integer sellStatus;//
	private String lotteryType;//
	private Date startTime;//
	private Date endTime;//
	private Date awardTime;//
	private Date createTime;//
	private Date updateTime;//
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIssueNo() {
		return this.issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSellStatus() {
		return this.sellStatus;
	}
	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}
	public String getLotteryType() {
		return this.lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public Date getStartTime() {
		return this.startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return this.endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getAwardTime() {
		return this.awardTime;
	}
	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime) {
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
		if (!(other instanceof TraditionIssue))
			return false;

		TraditionIssue castOther = (TraditionIssue) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("issueNo:").append(issueNo.toString()).append(",");
		builder.append("status:").append(status.toString()).append(",");
		builder.append("sellStatus:").append(sellStatus.toString()).append(",");
		builder.append("lotteryType:").append(lotteryType.toString()).append(",");
		builder.append("startTime:").append(startTime.toString()).append(",");
		builder.append("endTime:").append(endTime.toString()).append(",");
		builder.append("awardTime:").append(awardTime.toString()).append(",");
		builder.append("createTime:").append(createTime.toString()).append(",");
		builder.append("updateTime:").append(updateTime.toString());
		return builder.toString();
	}
}
