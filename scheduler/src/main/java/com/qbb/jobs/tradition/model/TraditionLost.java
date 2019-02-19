package com.qbb.jobs.tradition.model;

import java.util.Date;

public class TraditionLost {
    private Integer id;

    private String issueNo;

    private String num;

    private String countType;

    private String lotteryType;

    private String lostType;

    private Integer nowLostCount;

    private Integer maxLostCount;

    private Integer avgLostCount;

    private Integer lastLostCount;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getLostType() {
        return lostType;
    }

    public void setLostType(String lostType) {
        this.lostType = lostType;
    }

    public Integer getNowLostCount() {
        return nowLostCount;
    }

    public void setNowLostCount(Integer nowLostCount) {
        this.nowLostCount = nowLostCount;
    }

    public Integer getMaxLostCount() {
        return maxLostCount;
    }

    public void setMaxLostCount(Integer maxLostCount) {
        this.maxLostCount = maxLostCount;
    }

    public Integer getAvgLostCount() {
        return avgLostCount;
    }

    public void setAvgLostCount(Integer avgLostCount) {
        this.avgLostCount = avgLostCount;
    }

    public Integer getLastLostCount() {
        return lastLostCount;
    }

    public void setLastLostCount(Integer lastLostCount) {
        this.lastLostCount = lastLostCount;
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
}