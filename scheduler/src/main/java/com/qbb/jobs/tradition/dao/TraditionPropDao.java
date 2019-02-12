package com.qbb.jobs.tradition.dao;

import com.qbb.jobs.tradition.model.TraditionAward;
import com.qbb.jobs.tradition.model.TraditionProp;

import java.util.List;

public interface TraditionPropDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TraditionProp record);

    int insertSelective(TraditionProp record);

    TraditionProp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TraditionProp record);

    int updateByPrimaryKey(TraditionProp record);

}