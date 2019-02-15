
package com.qbb.jobs.award.service;

import com.qbb.util.db.IBaseService;
import com.qbb.jobs.award.domain.TraditionAward;


/**
 * 
 */
public interface ITraditionAwardService extends IBaseService<TraditionAward> {

	public void getAwardNom(String gameCode);
}
