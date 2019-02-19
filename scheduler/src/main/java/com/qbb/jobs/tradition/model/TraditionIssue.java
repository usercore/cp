package com.qbb.jobs.tradition.model;

import java.util.Date;

public class TraditionIssue {
    private Integer id;

    private String issueNo;

    private Integer status;

    private Integer sellStatus;

    private String lotteryType;

    private Date startTime;

    private Date endTime;

    private Date awardTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
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

    public TraditionIssue() {
    }

    public TraditionIssue(String issueNo, String lotteryType) {
        this.issueNo = issueNo;
        this.lotteryType = lotteryType;
    }

    @Override
    public String toString() {
        return "TraditionIssue{" +
                "id=" + id +
                ", issueNo='" + issueNo + '\'' +
                ", status=" + status +
                ", sellStatus=" + sellStatus +
                ", lotteryType='" + lotteryType + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", awardTime=" + awardTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}