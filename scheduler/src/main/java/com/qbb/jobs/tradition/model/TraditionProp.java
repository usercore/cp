package com.qbb.jobs.tradition.model;

import java.util.Date;

/**
 * 彩票属性
 */
public class TraditionProp {
    private Integer id;

    private String issueNo;
    /**
     * 彩种 201 双色球 101 大乐透 202 3D 102 排3 301:甘肃快三
     */
    private String lotteryType;
    /**
     * 属性类型 0 和值 1和尾 2跨度  3和值012路  4和尾012路  5跨度012路
     * 6和值形态-大小  7和值形态-单双  8号码形态
     */
    private String propType;

    private String propValue;

    private Date createTime;

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

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}