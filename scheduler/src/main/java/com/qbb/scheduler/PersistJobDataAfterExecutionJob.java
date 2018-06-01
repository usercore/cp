package com.qbb.scheduler;

import org.quartz.PersistJobDataAfterExecution;

/**
 * 当正常执行完Job后, JobDataMap中的数据应该被改动, 以被下一次调用时用。当使用@PersistJobDataAfterExecution
 * 注解时, 为了避免并发时, 存储数据造成混乱, 强烈建议把@DisallowConcurrentExecution注解也加上
 *
 */
@PersistJobDataAfterExecution
public abstract class PersistJobDataAfterExecutionJob extends DisallowConcurrentExecutionJob {

}
