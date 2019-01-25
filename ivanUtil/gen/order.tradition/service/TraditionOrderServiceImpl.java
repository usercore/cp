
package com.bc.order.tradition.service;


import com.bc.order.tradition.domain.TraditionOrder;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.order.tradition.dao.TraditionOrderMapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.bc.util.exception.BusinessException;
import java.util.List;
/**
 * 
 */
 @Service("traditionOrderService")
public class TraditionOrderServiceImpl extends BaseServiceImpl<TraditionOrder> implements ITraditionOrderService{

	@Autowired
	TraditionOrderMapper traditionOrderMapper;
	
	@Override
	protected IBaseMapper<TraditionOrder> getBaseMapper() {
		return traditionOrderMapper;
	}
}
