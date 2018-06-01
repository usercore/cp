package com.qbb.jobs.pub.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.qbb.jobs.pub.dao.SeqDao;
import com.qbb.jobs.pub.service.SeqService;

@Service
public class SeqServiceImpl implements SeqService{
	
	@Autowired
	private SeqDao seqDao;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public String seqGet(String sqnocd, String sqnodt) {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("Pi_Sqnocd", sqnocd);
		m.put("Pi_Sqnodt", sqnodt);
		m.put("Po_Sequno", "");
		m.put("po_errcode", "");
		m.put("po_errmsg", "");
		seqDao.getSeq(m);
		
		transactionManager.commit(status);
		
		return (String)m.get("Po_Sequno");
		
	}

}
