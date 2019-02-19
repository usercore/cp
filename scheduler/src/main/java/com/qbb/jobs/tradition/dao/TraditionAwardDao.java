package com.qbb.jobs.tradition.dao;

import com.qbb.jobs.tradition.model.TraditionAward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TraditionAwardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TraditionAward record);

    int insertSelective(TraditionAward record);

    TraditionAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TraditionAward record);

    int updateByPrimaryKey(TraditionAward record);

    TraditionAward getTraditionAwardByIssueNo(String issueNo);

    TraditionAward getLastTraditionAward();

    /**
     * 获取最新的记录指定条数
     *
     * @param lottery_type 彩种
     * @param count_type   需要查询的记录条数
     * @return
     */
    List<TraditionAward> getNewestAwardListByNum(@Param("lottery_type") String lottery_type, @Param("count_type") Integer count_type);

    List<TraditionAward> getAwardPropNotRecorded();
}