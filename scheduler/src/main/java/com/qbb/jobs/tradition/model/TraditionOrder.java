package com.qbb.jobs.tradition.model;

import java.math.BigDecimal;
import java.util.Date;

public class TraditionOrder {
    private Long id;

    private String userId;

    private String investNum;

    private String manner;

    private String issueNo;

    private String lotteryType;

    private Byte investCount;

    private BigDecimal investMoney;

    private Integer multiple;

    private Integer investManner;

    private Integer awardStatus;

    private String awardDetail;

    private BigDecimal awardMoney;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvestNum() {
        return investNum;
    }

    public void setInvestNum(String investNum) {
        this.investNum = investNum;
    }

    public String getManner() {
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public Byte getInvestCount() {
        return investCount;
    }

    public void setInvestCount(Byte investCount) {
        this.investCount = investCount;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public Integer getInvestManner() {
        return investManner;
    }

    public void setInvestManner(Integer investManner) {
        this.investManner = investManner;
    }

    public Integer getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(Integer awardStatus) {
        this.awardStatus = awardStatus;
    }

    public String getAwardDetail() {
        return awardDetail;
    }

    public void setAwardDetail(String awardDetail) {
        this.awardDetail = awardDetail;
    }

    public BigDecimal getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(BigDecimal awardMoney) {
        this.awardMoney = awardMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TraditionOrder{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", investNum='" + investNum + '\'' +
                ", manner='" + manner + '\'' +
                ", issueNo='" + issueNo + '\'' +
                ", lotteryType='" + lotteryType + '\'' +
                ", investCount=" + investCount +
                ", investMoney=" + investMoney +
                ", multiple=" + multiple +
                ", investManner=" + investManner +
                ", awardStatus=" + awardStatus +
                ", awardDetail='" + awardDetail + '\'' +
                ", awardMoney=" + awardMoney +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}