package com.qbb.jobs.tradition.dao;


import com.qbb.jobs.tradition.model.TraditionIssue;

public interface TraditionIssueDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TraditionIssue record);

    int insertSelective(TraditionIssue record);

    TraditionIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TraditionIssue record);

    int updateByPrimaryKey(TraditionIssue record);

    TraditionIssue selectByIssueAndLotteryType(TraditionIssue traditionIssue);

    TraditionIssue getNextIssueToOpen();

    int updateByIssueNoAndLotteryType(TraditionIssue record);

}