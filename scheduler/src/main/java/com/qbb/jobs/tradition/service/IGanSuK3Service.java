package com.qbb.jobs.tradition.service;

import com.qbb.scheduler.model.JobModel;

/**
 * 甘肃快三处理
 *
 * @author ouk 20180719
 */
public interface IGanSuK3Service {
    /**
     * 新增待开奖数据
     *
     * @param jobModel
     * @throws Exception
     * @author ouk
     * @date 20190218
     */
    void createNextAward(JobModel jobModel) throws Exception;

    /**
     * 处理开奖结果数据
     *
     * @param jobModel
     * @throws Exception
     * @author ouk
     * @date 20180719
     */
    void dealWithResult(JobModel jobModel) throws Exception;
}
