package com.qbb.scheduler.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.qbb.scheduler.mapper.SchedulerMapper;
import com.qbb.scheduler.model.JobModel;
import com.qbb.scheduler.model.JobState;
import com.qbb.scheduler.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}

	@Override
	public void shutdown() throws SchedulerException {
		schedulerFactoryBean.getScheduler().shutdown();
	}

	@Override
	public void scheduleJob(JobModel job) throws ClassNotFoundException, SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		@SuppressWarnings("unchecked")
		JobDetail jobDetail = JobBuilder.newJob((Class<Job>) Class.forName(job.getClazz())).withIdentity(job.getName(), job.getGroup())
				.withDescription(job.getDescription()).build();
		JobDataMap data = jobDetail.getJobDataMap();
		data.put("job_info", job);
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
		if (job.getMisfire() != null) {
			switch (job.getMisfire()) {
			case 1:
				// 以当前时间为触发频率立刻触发一次执行
				cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
				break;
			case 2:
				// 以当前时间为触发频率立即触发执行
				cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
				break;
			default:
				// 不触发立即执行
				cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
				break;
			}
		}
		TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup())
				.withDescription(job.getDescription()).withSchedule(cronScheduleBuilder);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (job.getStartAt() != null && !job.getStartAt().equals("")) {
			try {
				// 设置开始时间
				triggerBuilder.startAt(format.parse(job.getStartAt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (job.getEndAt() != null && !job.getEndAt().equals("")) {
			try {
				triggerBuilder.endAt(format.parse(job.getEndAt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		CronTrigger trigger = triggerBuilder.build();
		scheduler.deleteJob(JobKey.jobKey(job.getName(), job.getGroup()));
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.resumeJob(JobKey.jobKey(job.getName(), job.getGroup()));
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}

	@Override
	public void pauseJob(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(name, group);
		scheduler.pauseJob(jobKey);
	}

	@Override
	public void resumeJob(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(name, group);
		scheduler.resumeJob(jobKey);
	}

	@Override
	public void pauseAll() throws SchedulerException {
		List<JobModel> list = mapper.getAllJob(schedulerFactoryBean.getScheduler().getSchedulerName());
		if(list != null) {
			for (JobModel jobModel : list) {
				pauseJob(jobModel.getName(), jobModel.getGroup());
			}
		}
		//Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//scheduler.pauseAll();
	}

	@Override
	public void resumeAll() throws SchedulerException {
		List<JobModel> list = mapper.getAllJob(schedulerFactoryBean.getScheduler().getSchedulerName());
		if(list != null) {
			for (JobModel jobModel : list) {
				resumeJob(jobModel.getName(), jobModel.getGroup());
			}
		}
		//Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//scheduler.resumeAll();
	}

	@Override
	public void deleteJob(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(name, group);
		scheduler.deleteJob(jobKey);
	}

	@Override
	public void start() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.start();
	}

	@Override
	public void reload() throws ClassNotFoundException, SchedulerException {
		List<JobModel> list = getAllJob();
		if (list != null && !list.isEmpty()) {
			pauseAll();
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			while (scheduler.getCurrentlyExecutingJobs().size() > 0) {
				try {
					// 等待2秒，让正在运行中的任务停止（有可能仍在运行中，需要在任务中作判断，判断任务是否已暂停，暂停直接结束任务）
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < list.size(); i++) {
				scheduleJob(list.get(i));
			}
			resumeAll();
		}
	}

	@Autowired
	private SchedulerMapper mapper;

	public void setMapper(SchedulerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<JobModel> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		return mapper.getAllJob(scheduler.getSchedulerName());
	}

	@Override
	public JobModel getJobInfo(long id) {
		return mapper.getJobInfo(id);
	}

	@Override
	public int saveOrUpdate(JobModel job) throws SchedulerException {
		int rs;
		if (job.getId() != null) {
			rs = mapper.updateJob(job);
		} else {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			job.setSchedName(scheduler.getSchedulerName());
			rs = mapper.addJob(job);
		}
		return rs;
	}

	@Override
	public int delete(long id) {
		return mapper.deleteJob(id);
	}

	@Override
	public List<JobState> getTaskState(Long id) {
		return mapper.getTaskState(id);
	}

	@Override
	public int execute(String s) {
		return 0;
	}

	@Override
	public int runnow(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey(name, group);
		if(!scheduler.getTriggersOfJob(jobKey).isEmpty()) {
			scheduler.triggerJob(jobKey);
			return 1;
		}else{
			return 0;
		}
	}

}
