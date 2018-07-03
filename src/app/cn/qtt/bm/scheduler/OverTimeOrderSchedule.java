/**
 * 
 */
package app.cn.qtt.bm.scheduler;

import java.util.Date;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;

/**
 * @author Gabriel
 * @Description 订单超时任务调度程序
 * @date 2013-7-4 下午3:49:07
 * @type OverTimeOrderSchedule
 * @project BespeakMeal
 */
public class OverTimeOrderSchedule extends BaseSchedulerTask {
private static CCrppLog4j log = new CCrppLog4j(OverTimeOrderSchedule.class.getName());
	
	public OverTimeOrderSchedule() {
	}

	private DateUtil dateUtil = new DateUtil();
	private String scheduleName = "OverTimeOrderSchedule"; // 任务进程的名称
	protected Date taskBeginTime = dateUtil.getToday(); // 本次任务处理开始时间
	protected int totalTasks = 10; // 本次要处理的任务总数
	protected int remainTasks = 0; // 本次尚未处理的任务数
	protected String currentTaskName = "订单超时下发任务调度"; // 当前正在处理的任务名称
	protected Date lastTaskBeginTime = null; // 上次任务处理开始时间
	protected String taskDescription = null; // 当前任务情况描述
	
	private IOverTimeOrderQuartzJobBean overTimeOrderQuartzJob;

	@Override
	public void process() {
		
		log.info("开始处理任务：" + scheduleName);
		/********* 记录任务信息 ************/
		this.setSchedulerName_1("OverTimeOrderSchedule");
		this.setJobName(this.scheduleName);
		this.setTotalTasks(1); // 取得总任务数
		this.jobBegin();

		/********* 记录作业信息 ************/
		this.setCurrentTaskName("OverTimeOrderScheduleService"); // 记录当前要处理的文件名
		this.setCurrentTaskDescription("订单超时调度任务服务");
		this.setCurrentTaskRecords(1);
		this.taskBegin();
		try {
			overTimeOrderQuartzJob.startJob();
			this.setCurrentTaskState(BaseSchedulerTask.TASK_STATE_SUCCESS); // 记录作业执行状态
		} catch (Exception ex) {
			this.setCurrentTaskState(BaseSchedulerTask.TASK_STATE_FAILED); // 记录作业执行状态
			this.setCurrentTaskStateDescription(ex.getMessage()); // 记录当前作业处理异常说明
			log.exception(currentTaskName+"JOB异常", ex);
		}
		this.taskFinished(); // 触发作业完成事件
		this.jobFinished();
	}
	
	/**
	 * @return the scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * @param scheduleName the scheduleName to set
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	/**
	 * @return the overTimeOrderQuartzJob
	 */
	public IOverTimeOrderQuartzJobBean getOverTimeOrderQuartzJob() {
		return overTimeOrderQuartzJob;
	}

	/**
	 * @param overTimeOrderQuartzJob the overTimeOrderQuartzJob to set
	 */
	public void setOverTimeOrderQuartzJob(IOverTimeOrderQuartzJobBean overTimeOrderQuartzJob) {
		this.overTimeOrderQuartzJob = overTimeOrderQuartzJob;
	}
	
}
