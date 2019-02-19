package com.qbb.jobs.tradition.dao;

import com.qbb.jobs.tradition.model.TraditionOrder;

public interface TraditionOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TraditionOrder record);

    int insertSelective(TraditionOrder record);

    TraditionOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TraditionOrder record);

    int updateByPrimaryKey(TraditionOrder record);
}