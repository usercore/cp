
package com.bc.order.tradition.dao;


import com.bc.util.db.IBaseMapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.order.tradition.domain.TraditionOrder;

/**
 * 
 */
public interface TraditionOrderMapper extends IBaseMapper<TraditionOrder> {

	/**
	 * 更新未中奖订单
	 * @param prizeManner
	 * @return
	 */
	public int updateK3NotPrize(@Param("prizeManner") List<String> prizeManner,@Param("issueNo")String issueNo);
}
