package com.qbb.jobs.tradition;

import com.qbb.jobs.tradition.service.IGanSuK3LostService;
import com.qbb.jobs.tradition.service.IGanSuK3PropService;
import com.qbb.jobs.tradition.service.IGanSuK3Service;
import com.qbb.scheduler.DisallowConcurrentExecutionJob;
import com.qbb.scheduler.service.IScheduleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 计算遗漏数据
 *
 * @author ouk
 * @version 2019/02/18 22:27
 */
public class RunGanSuK3LostJob extends DisallowConcurrentExecutionJob {

    @Autowired
    IScheduleService scheduleService;
    @Autowired
    IGanSuK3LostService iGanSuK3LostService;

    public void setScheduleService(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            iGanSuK3LostService.doCalculateLost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
