/**
 * 
 */
package app.cn.qtt.bm.scheduler;

import java.util.Date;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;

/**
 * @author Gabriel
 * @Description 密码重置短信下发调度任务
 * @date 2013-7-4 下午3:01:48
 * @type SmsPwdRstSendSchedule
 * @project BespeakMeal
 */
public class SmsPwdRstSendSchedule extends BaseSchedulerTask {
private static CCrppLog4j log = new CCrppLog4j(SmsPwdRstSendSchedule.class.getName());
	
	public SmsPwdRstSendSchedule() {
	}

	private DateUtil dateUtil = new DateUtil();
	private String scheduleName = "SmsPwdRstSendSchedule"; // 任务进程的名称
	protected Date taskBeginTime = dateUtil.getToday(); // 本次任务处理开始时间
	protected int totalTasks = 10; // 本次要处理的任务总数
	protected int remainTasks = 0; // 本次尚未处理的任务数
	protected String currentTaskName = "订餐系统密码重置短信下发任务调度"; // 当前正在处理的任务名称
	protected Date lastTaskBeginTime = null; // 上次任务处理开始时间
	protected String taskDescription = null; // 当前任务情况描述
	
	private SendScheduleService sendScheduleService;

	@Override
	public void process() {
		
		log.info("开始处理任务：" + scheduleName);
		/********* 记录任务信息 ************/
		this.setSchedulerName_1("SmsPwdRstSendSchedule");
		this.setJobName(this.scheduleName);
		this.setTotalTasks(1); // 取得总任务数
		this.jobBegin();

		/********* 记录作业信息 ************/
		this.setCurrentTaskName("SmsPwdRstSendScheduleService"); // 记录当前要处理的文件名
		this.setCurrentTaskDescription("密码重置短信下发调度任务服务");
		this.setCurrentTaskRecords(1);
		this.taskBegin();
		try {
			sendScheduleService.processSendSchedule();
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
	 * @return the sendScheduleService
	 */
	public SendScheduleService getSendScheduleService() {
		return sendScheduleService;
	}

	/**
	 * @param sendScheduleService the sendScheduleService to set
	 */
	public void setSendScheduleService(SendScheduleService sendScheduleService) {
		this.sendScheduleService = sendScheduleService;
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
}
