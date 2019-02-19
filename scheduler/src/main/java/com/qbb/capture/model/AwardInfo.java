package com.qbb.capture.model;

import com.google.common.base.Objects;
import com.qbb.capture.enums.LotteryTypeEnum;

public class AwardInfo {

	/**
	 * 彩种
	 */
	private String lotteryType;
	/**
	 * 期号
	 */
	private String issueNo;
	
	/**
	 * 开奖号码
	 */
	private String awardNum;
	
	/**
	 * 开奖详情
	 */
	private String prizeDetail;
	
	/**
	 * 快3和值
	 */
	private Integer sumValue;
	
	/**
	 * 快3跨度
	 */
	private Integer spanValue;

	public AwardInfo(){
		
	}
	
	public AwardInfo(String lotteryType){
		this.lotteryType = lotteryType;
	}
	
	public AwardInfo(LotteryTypeEnum typeEnum){
		this.typeEnum = typeEnum;
	}
	
	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getAwardNum() {
		return awardNum;
	}

	public void setAwardNum(String awardNum) {
		this.awardNum = awardNum;
	}

	public String getPrizeDetail() {
		return prizeDetail;
	}

	public void setPrizeDetail(String prizeDetail) {
		this.prizeDetail = prizeDetail;
	}

	@Override
	public String toString() {
		return "彩种："+this.lotteryType + "期号：" + this.issueNo + " -> 开奖号码：" + this.awardNum + "详情："+this.prizeDetail;
	}

	@Override
	public boolean equals(Object obj) {
		 if (this == obj) {
			 return true; 
		 }
		 if (obj == null) {
			 return false; 
		 }
		 if (getClass() != obj.getClass()) {
			 return false; 
		 }
		 AwardInfo info = (AwardInfo) obj;
		 return Objects.equal(this.lotteryType, info.getLotteryType()) && Objects.equal(this.issueNo, info.getIssueNo())
				 && Objects.equal(this.awardNum, info.getAwardNum());
			  
	}
	
	private LotteryTypeEnum typeEnum;
	
	public LotteryTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(LotteryTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public Integer getSumValue() {
		return sumValue;
	}

	public void setSumValue(Integer sumValue) {
		this.sumValue = sumValue;
	}

	public Integer getSpanValue() {
		return spanValue;
	}

	public void setSpanValue(Integer spanValue) {
		this.spanValue = spanValue;
	}

	public boolean isCheck(){
		return this.awardNum.matches(typeEnum.getRegex());
	}
	
	public static void main(String[] args) {
		LotteryTypeEnum typeEnum = LotteryTypeEnum.D3;
		
		AwardInfo info = new AwardInfo(typeEnum);
		info.setAwardNum("1/2/3");
		System.out.println(info.isCheck());
		AwardInfo info1 = new AwardInfo(typeEnum);
		System.out.println(info.equals(info1));
	}
}
