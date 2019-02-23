
package com.bc.filter.scheme.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.bc.util.db.BasePage;

/**
 * filter_scheme
 * 
 * 
 */
public class FilterScheme extends BasePage implements Serializable {
	private static final long serialVersionUID = "FilterScheme".hashCode();
	
	private Long id;//
	private String schemeId;//
	private String userId;//
	private String schemeDetail;//
	private Integer multiple;//
	private String startIssueNo;//
	private String lotteryType;//
	private Integer continuousCount;//
	private BigDecimal investMoney;//
	private Integer awardStop;//
	private Integer awardStatus;//
	private String awardDetail;//
	private BigDecimal awardMoney;//
	private Date createTime;//
	private Date updateTime;//
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSchemeId() {
		return this.schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSchemeDetail() {
		return this.schemeDetail;
	}
	public void setSchemeDetail(String schemeDetail) {
		this.schemeDetail = schemeDetail;
	}
	public Integer getMultiple() {
		return this.multiple;
	}
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	public String getStartIssueNo() {
		return this.startIssueNo;
	}
	public void setStartIssueNo(String startIssueNo) {
		this.startIssueNo = startIssueNo;
	}
	public String getLotteryType() {
		return this.lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public Integer getContinuousCount() {
		return this.continuousCount;
	}
	public void setContinuousCount(Integer continuousCount) {
		this.continuousCount = continuousCount;
	}
	public BigDecimal getInvestMoney() {
		return this.investMoney;
	}
	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}
	public Integer getAwardStop() {
		return this.awardStop;
	}
	public void setAwardStop(Integer awardStop) {
		this.awardStop = awardStop;
	}
	public Integer getAwardStatus() {
		return this.awardStatus;
	}
	public void setAwardStatus(Integer awardStatus) {
		this.awardStatus = awardStatus;
	}
	public String getAwardDetail() {
		return this.awardDetail;
	}
	public void setAwardDetail(String awardDetail) {
		this.awardDetail = awardDetail;
	}
	public BigDecimal getAwardMoney() {
		return this.awardMoney;
	}
	public void setAwardMoney(BigDecimal awardMoney) {
		this.awardMoney = awardMoney;
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
		if (!(other instanceof FilterScheme))
			return false;

		FilterScheme castOther = (FilterScheme) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("schemeId:").append(schemeId.toString()).append(",");
		builder.append("userId:").append(userId.toString()).append(",");
		builder.append("schemeDetail:").append(schemeDetail.toString()).append(",");
		builder.append("multiple:").append(multiple.toString()).append(",");
		builder.append("startIssueNo:").append(startIssueNo.toString()).append(",");
		builder.append("lotteryType:").append(lotteryType.toString()).append(",");
		builder.append("continuousCount:").append(continuousCount.toString()).append(",");
		builder.append("investMoney:").append(investMoney.toString()).append(",");
		builder.append("awardStop:").append(awardStop.toString()).append(",");
		builder.append("awardStatus:").append(awardStatus.toString()).append(",");
		builder.append("awardDetail:").append(awardDetail.toString()).append(",");
		builder.append("awardMoney:").append(awardMoney.toString()).append(",");
		builder.append("createTime:").append(createTime.toString()).append(",");
		builder.append("updateTime:").append(updateTime.toString());
		return builder.toString();
	}
}
