
package com.bc.game.issue.service;


import com.bc.game.issue.domain.TraditionIssue;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.game.issue.dao.TraditionIssueMapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.bc.util.exception.BusinessException;
import java.util.List;
/**
 * 
 */
 @Service("traditionIssueService")
public class TraditionIssueServiceImpl extends BaseServiceImpl<TraditionIssue> implements ITraditionIssueService{

	@Autowired
	TraditionIssueMapper traditionIssueMapper;
	
	@Override
	protected IBaseMapper<TraditionIssue> getBaseMapper() {
		return traditionIssueMapper;
	}
}
