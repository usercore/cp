
package com.bc.game.award.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.bc.util.db.BasePage;

/**
 * tradition_award
 * 
 * 
 */
public class TraditionAward extends BasePage implements Serializable {
	private static final long serialVersionUID = "TraditionAward".hashCode();
	
	private Integer id;//
	private String issueNo;//
	private Integer status;//
	private String lotteryType;//
	private String awardNum;//
	private String prizeDetail;//
	private String lost;//
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
	public String getLotteryType() {
		return this.lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public String getAwardNum() {
		return this.awardNum;
	}
	public void setAwardNum(String awardNum) {
		this.awardNum = awardNum;
	}
	public String getPrizeDetail() {
		return this.prizeDetail;
	}
	public void setPrizeDetail(String prizeDetail) {
		this.prizeDetail = prizeDetail;
	}
	public String getLost() {
		return this.lost;
	}
	public void setLost(String lost) {
		this.lost = lost;
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
		if (!(other instanceof TraditionAward))
			return false;

		TraditionAward castOther = (TraditionAward) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("issueNo:").append(issueNo.toString()).append(",");
		builder.append("status:").append(status.toString()).append(",");
		builder.append("lotteryType:").append(lotteryType.toString()).append(",");
		builder.append("awardNum:").append(awardNum.toString()).append(",");
		builder.append("prizeDetail:").append(prizeDetail.toString()).append(",");
		builder.append("lost:").append(lost.toString()).append(",");
		builder.append("createTime:").append(createTime.toString()).append(",");
		builder.append("updateTime:").append(updateTime.toString());
		return builder.toString();
	}
}
