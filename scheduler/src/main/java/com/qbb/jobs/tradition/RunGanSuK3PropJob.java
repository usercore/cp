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
 * 抓取甘肃快三开奖结果
 *
 * @author ouk
 * @version 2019/02/18 22:28
 */
public class RunGanSuK3PropJob extends DisallowConcurrentExecutionJob {

    @Autowired
    IScheduleService scheduleService;
    @Autowired
    IGanSuK3PropService iGanSuK3PropService;

    public void setScheduleService(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            iGanSuK3PropService.doCalculateProp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
