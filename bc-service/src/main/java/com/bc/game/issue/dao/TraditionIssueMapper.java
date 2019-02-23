
package com.bc.game.issue.dao;


import com.bc.util.db.IBaseMapper;
import com.bc.game.issue.domain.TraditionIssue;

/**
 * 
 */
public interface TraditionIssueMapper extends IBaseMapper<TraditionIssue> {

	public int updateIssueStatus(TraditionIssue traditionIssue);
}
