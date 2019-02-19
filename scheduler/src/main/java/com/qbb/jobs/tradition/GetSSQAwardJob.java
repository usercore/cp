package com.qbb.jobs.tradition;

import com.qbb.jobs.tradition.service.ITraditionAwardService;
import com.qbb.scheduler.DisallowConcurrentExecutionJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetSSQAwardJob extends DisallowConcurrentExecutionJob{

	@Autowired
	private ITraditionAwardService traditionAwardService;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		traditionAwardService.getAwardNom("201");
	}
    
}