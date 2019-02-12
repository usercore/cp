package com.qbb.jobs.tradition.service;

import com.qbb.capture.model.AwardInfo;

/**
 * 处理甘肃快三遗漏数据
 */
public interface IGanSuK3LostService {
    /**
     * 处理遗漏数据 定时任务使用
     */
    void doCalculateLost();

}
