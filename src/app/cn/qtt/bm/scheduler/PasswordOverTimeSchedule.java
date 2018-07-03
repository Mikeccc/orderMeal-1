/*
 * 文 件 名:  PasswordOverTimeSchedule.java
 * 版    权:  QTT Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Gabriel
 * 修改时间:  2013-8-23
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package app.cn.qtt.bm.scheduler;

import java.util.Date;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;

/**
 * 密码失效job
 * @project BespeakMeal
 * @author Gabriel
 * @createtime 2013-8-23 下午7:49:04
 * @modify_description 
 * @mender  
 * @modifytime 
 * @version 1.0
 * @since JDK 1.6
 */
public class PasswordOverTimeSchedule extends BaseSchedulerTask {
	private static CCrppLog4j log = new CCrppLog4j(PasswordOverTimeSchedule.class.getName());
	private IPasswordOverTimeQuartzJobBean passwordOverTimeQuartzJob;
	
	private DateUtil dateUtil = new DateUtil();
	private String scheduleName = "PasswordOverTimeSchedule"; // 任务进程的名称
	protected Date taskBeginTime = dateUtil.getToday(); // 本次任务处理开始时间
	protected int totalTasks = 10; // 本次要处理的任务总数
	protected int remainTasks = 0; // 本次尚未处理的任务数
	protected String currentTaskName = "密码失效任务调度"; // 当前正在处理的任务名称
	protected Date lastTaskBeginTime = null; // 上次任务处理开始时间
	protected String taskDescription = null; // 当前任务情况描述
	
	/**
	 * @see app.cn.qtt.bm.scheduler.BaseSchedulerTask#process()
	 * @author Gabriel
	 * @createtime 2013-8-23 下午7:49:04
	 */
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
		this.setCurrentTaskDescription("密码失效调度任务服务");
		this.setCurrentTaskRecords(1);
		this.taskBegin();
		try {
			passwordOverTimeQuartzJob.startJob();
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
	 * @return 返回 passwordOverTimeQuartzJob
	 */
	public IPasswordOverTimeQuartzJobBean getPasswordOverTimeQuartzJob() {
		return passwordOverTimeQuartzJob;
	}

	/**
	 * @param 对passwordOverTimeQuartzJob进行赋值
	 */
	public void setPasswordOverTimeQuartzJob(IPasswordOverTimeQuartzJobBean passwordOverTimeQuartzJob) {
		this.passwordOverTimeQuartzJob = passwordOverTimeQuartzJob;
	}

	/**
	 * @return 返回 scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * @param 对scheduleName进行赋值
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

}
