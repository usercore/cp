package com.qbb.jobs.award;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qbb.jobs.award.service.ITraditionAwardService;
import com.qbb.scheduler.DisallowConcurrentExecutionJob;

public class GetSSQAwardJob extends DisallowConcurrentExecutionJob{

	@Autowired
	private ITraditionAwardService traditionAwardService;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		traditionAwardService.getAwardNom("201");
	}

}