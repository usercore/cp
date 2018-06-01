package com.qbb.jobs.ms;



import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qbb.jobs.ms.service.IMonitorSystemService;
import com.qbb.scheduler.AbstractJob;
import com.qbb.scheduler.service.IScheduleService;


/**
 * 系统监控业务
 *
 */
public class RunEveryMomentJob extends AbstractJob {

	@Autowired
	private IScheduleService scheduleService;
	

	@Autowired
	private IMonitorSystemService monitorSystemService;
	
	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//实时监控数据
			try {
				monitorSystemService.runEveryMoment(this.getJobInfo());
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		
	}



}
