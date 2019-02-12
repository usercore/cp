package com.qbb.jobs.tradition.dao;

import com.qbb.jobs.tradition.model.TraditionLost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TraditionLostDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TraditionLost record);

    int insertSelective(TraditionLost record);

    TraditionLost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TraditionLost record);

    int updateByPrimaryKey(TraditionLost record);

    /**
     * 查询遗漏记录
     *
     * @param lottery_type 彩种 201 双色球 101 大乐透 202 3D 102 排3 301:甘肃快三
     * @param count_type   统计类型 0 所有数据  10 近10期  20 近20期  50 100 200 500
     * @param lost_type    遗漏类型 0 和值 1跨度 2和尾 3热度 4和值012路 5跨度012路 6和尾012路 7和值形态(大小) 8和值形态(奇偶) 9号码形态
     * @return
     */
    TraditionLost getLostInfoByParam(@Param("lottery_type") String lottery_type, @Param("count_type") String count_type,
                                     @Param("lost_type") String lost_type);


    /**
     * 查询遗漏记录
     *
     * @param record
     * @return
     */
    TraditionLost getLostInfo(TraditionLost record);

    /**
     * 更新遗漏记录
     *
     * @param record
     * @return
     */
    int updateTraditionLost(TraditionLost record);

    /**
     * 获取最新一期开奖数据的遗漏数据
     *
     * @return
     */
    TraditionLost getLastAwardLostInfo();

    /**
     * 查询遗漏记录
     *
     * @param record
     * @return
     */
    List<TraditionLost> getLostInfoList(TraditionLost record);

}