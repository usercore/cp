
package com.bc.order.tradition.domain;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import com.bc.util.db.BasePage;

/**
 * tradition_order
 * 
 * 
 */
public class TraditionOrder extends BasePage implements Serializable {
	private static final long serialVersionUID = "TraditionOrder".hashCode();
	
	//0 未算奖 1 中奖 2 未中奖
	public static final int NOT_CALCULATE = 0;
	public static final int SEND_PRIZE = 1;
	public static final int NOT_PRIZE = 2;
	
	private Long id;//
	private String orderId;//
	private String userId;//
	private String investNum;//
	private String manner;//
	private String issueNo;//
	private String lotteryType;//
	private Integer investCount;//
	private BigDecimal investMoney;//
	private Integer multiple;//
	private Integer investManner;//
	private Integer awardStatus;//
	private String awardDetail;//
	private BigDecimal awardMoney;//
	private Date createTime;//
	private Date updateTime;//
	private String schemeId;//
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInvestNum() {
		return this.investNum;
	}
	public void setInvestNum(String investNum) {
		this.investNum = investNum;
	}
	public String getManner() {
		return this.manner;
	}
	public void setManner(String manner) {
		this.manner = manner;
	}
	public String getIssueNo() {
		return this.issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getLotteryType() {
		return this.lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public Integer getInvestCount() {
		return this.investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public BigDecimal getInvestMoney() {
		return this.investMoney;
	}
	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}
	public Integer getMultiple() {
		return this.multiple;
	}
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	public Integer getInvestManner() {
		return this.investManner;
	}
	public void setInvestManner(Integer investManner) {
		this.investManner = investManner;
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
	public String getSchemeId() {
		return this.schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
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
		if (!(other instanceof TraditionOrder))
			return false;

		TraditionOrder castOther = (TraditionOrder) other;

		return
			((this.id == castOther.getId()) || (this.id != null && castOther.getId() != null && this.id.equals(castOther.getId())))
		;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id.toString()).append(",");
		builder.append("orderId:").append(orderId.toString()).append(",");
		builder.append("userId:").append(userId.toString()).append(",");
		builder.append("investNum:").append(investNum.toString()).append(",");
		builder.append("manner:").append(manner.toString()).append(",");
		builder.append("issueNo:").append(issueNo.toString()).append(",");
		builder.append("lotteryType:").append(lotteryType.toString()).append(",");
		builder.append("investCount:").append(investCount.toString()).append(",");
		builder.append("investMoney:").append(investMoney.toString()).append(",");
		builder.append("multiple:").append(multiple.toString()).append(",");
		builder.append("investManner:").append(investManner.toString()).append(",");
		builder.append("awardStatus:").append(awardStatus.toString()).append(",");
		builder.append("awardDetail:").append(awardDetail.toString()).append(",");
		builder.append("awardMoney:").append(awardMoney.toString()).append(",");
		builder.append("createTime:").append(createTime.toString()).append(",");
		builder.append("updateTime:").append(updateTime.toString()).append(",");
		builder.append("schemeId:").append(schemeId.toString());
		return builder.toString();
	}
}
