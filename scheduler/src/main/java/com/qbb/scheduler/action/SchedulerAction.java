package com.qbb.scheduler.action;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qbb.scheduler.model.JobModel;
import com.qbb.scheduler.model.JobState;
import com.qbb.scheduler.service.IScheduleService;

@Controller
@ParentPackage("qbb-struts-default")
@Namespace("/scheduler")
public class SchedulerAction extends BaseAction<JobModel> {

	private static final long serialVersionUID = 2017199872716385357L;
	private static Pattern pattern = Pattern.compile("^\\d{4}\\-\\d{2}\\-\\d{2}\\s{1}\\d{2}\\:\\d{2}\\:\\d{2}$");
	@Autowired
	private IScheduleService scheduleService;
	private Long id;

	/**
	 * 任务开始
	 * 
	 * @return
	 */
	@Action("start")
	public String start() {
		try {
			scheduleService.start();
			ajaxVO.set(1, " 任务已开始运行");
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 立即运行
	 * 
	 * @return
	 */
	@Action("runnow")
	public String runnow() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				int r = scheduleService.runnow(job.getName(), job.getGroup());
				if (r > 0) {
					ajaxVO.set(1, "执行成功");
				} else {
					ajaxVO.set(0, "执行任务之前需保证任务是开始或停止状态");
				}
			} else {
				ajaxVO.set(0, "执行失败，找不到相关记录");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 任务重新加载
	 * 
	 * @return
	 */
	@Action("reload")
	public String reload() {
		try {
			scheduleService.reload();
			ajaxVO.set(1, " 任务重新加载完成");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 添加任务到任务管理器，并按所指定的触发机制执行任务
	 * 
	 * @return
	 */
	@Action("scheduleJob")
	public String scheduleJob() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				scheduleService.scheduleJob(job);
				ajaxVO.set(1, "启动成功");
			} else {
				ajaxVO.set(0, "启动失败，找不到相关记录");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 暂停正在执行中的任务
	 * 
	 * @return
	 */
	@Action("pauseJob")
	public String pauseJob() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				scheduleService.pauseJob(job.getName(), job.getGroup());
				ajaxVO.set(1, "任务已暂停运行");
			} else {
				ajaxVO.set(0, "暂停失败，找不到相关记录");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 暂停所有正在执行中的任务
	 * 
	 * @return
	 */
	@Action("pauseAll")
	public String pauseAll() {
		try {
			scheduleService.pauseAll();
			ajaxVO.set(1, "所有任务已暂停运行");
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 恢复已暂停的任务
	 * 
	 * @return
	 */
	@Action("resumeJob")
	public String resumeJob() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				scheduleService.resumeJob(job.getName(), job.getGroup());
				ajaxVO.set(1, "任务已恢复运行");
			} else {
				ajaxVO.set(0, "任务恢复失败，找不到相关记录");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 恢复所有已暂停的任务
	 * 
	 * @return
	 */
	@Action("resumeAll")
	public String resumeAll() {
		try {
			scheduleService.resumeAll();
			ajaxVO.set(1, "所有任务已恢复运行");
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 删除任务
	 * 
	 * @return
	 */
	@Action("deleteJob")
	public String deleteJob() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				scheduleService.deleteJob(job.getName(), job.getGroup());
				scheduleService.delete(id);
				ajaxVO.set(1, "删除成功");
			} else {
				ajaxVO.set(0, "删除失败，找不到相关记录");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 添加一个新任务
	 * 
	 * @return
	 */
	@Action("addJob")
	public String addJob() {
		try {
			if (args != null) {
				String msg = checkJobModel(args);
				if (msg == null) {
					args.setCreateTime(new Date());
					scheduleService.saveOrUpdate(args);
					ajaxVO.set(1, "添加成功");
				} else {
					ajaxVO.set(0, msg);
				}
			} else {
				ajaxVO.set(0, "添加失败，参数有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 修改任务属性
	 * 
	 * @return
	 */
	@Action("updateJob")
	public String updateJob() {
		try {
			if (args != null && args.getId() != null) {
				String msg = checkJobModel(args);
				if (msg == null) {
					JobModel job = scheduleService.getJobInfo(args.getId());
					if (job != null) {
						scheduleService.deleteJob(job.getName(), job.getGroup());
						scheduleService.saveOrUpdate(args);
					} else {
						ajaxVO.set(0, "更新失败，找不到相关记录");
					}
					ajaxVO.set(1, "更新成功");
				} else {
					ajaxVO.set(0, msg);
				}
			} else {
				ajaxVO.set(0, "更新失败，参数有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	private String checkJobModel(JobModel job) {
		if (job.getName() == null || job.getName().equals("")) {
			return "任务名称不能为空";
		}

		if (job.getGroup() == null || job.getGroup().equals("")) {
			return "任务组名称不能为空";
		}

		if (job.getClazz() == null || job.getClazz().equals("")) {
			return "任务处理类不能为空";
		}

		if (job.getCron() == null || job.getCron().equals("")) {
			return "任务触发条件不能为空";
		}
		if (job.getStartAt() != null && !job.getStartAt().equals("") && !pattern.matcher(job.getStartAt()).find()) {
			return "任务开始时间格式有误，正确格式为：yyyy-MM-dd HH:mm:ss";
		}

		if (job.getEndAt() != null && !job.getEndAt().equals("") && !pattern.matcher(job.getEndAt()).find()) {
			return "任务截止时间格式有误，正确格式为：yyyy-MM-dd HH:mm:ss";
		}
		return null;
	}

	/**
	 * 获取所有任务
	 * 
	 * @return
	 */
	@Action("getAllJob")
	public String getAllJob() {
		try {
			List<JobModel> list = scheduleService.getAllJob();
			ajaxVO.set(1, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 获取指定任务信息
	 * 
	 * @return
	 */
	@Action("getJobInfo")
	public String getJobInfo() {
		try {
			JobModel job = scheduleService.getJobInfo(id);
			if (job != null) {
				ajaxVO.set(1, "查询成功", job);
			} else {
				ajaxVO.set(0, "查询失败，找不到相关记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	/**
	 * 关闭任务管理器，如果调用此方法，必须重启服务
	 * 
	 * @return
	 */
	@Action("shutdown")
	public String shutdown() {
		try {
			scheduleService.shutdown();
			ajaxVO.set(1, "服务已终止，如需重新运行，请重启服务！");
		} catch (SchedulerException e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getMessage());
		}
		return resultAjaxVO();
	}

	@Action("getTaskState")
	public String getTaskState() {
		try {
			List<JobState> list = scheduleService.getTaskState(id);
			ajaxVO.set(1, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxVO.set(0, e.getClass().getName() + ": " + e.getMessage());
		}
		return resultAjaxVO();
	}

	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
