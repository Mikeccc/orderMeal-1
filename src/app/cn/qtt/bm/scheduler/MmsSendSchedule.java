/**
 * 
 */
package app.cn.qtt.bm.scheduler;

import java.util.Calendar;
import java.util.Date;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;

/**
 * @author Gabriel
 * @Description 彩信下发任务调度
 * @date 2013-7-4 下午2:41:32
 * @type MmsSendSchedule
 * @project BespeakMeal
 */
public class MmsSendSchedule extends BaseSchedulerTask{
	private static CCrppLog4j log = new CCrppLog4j(MmsSendSchedule.class.getName());
	
	public MmsSendSchedule() {
	}

	private DateUtil dateUtil = new DateUtil();
	private String scheduleName = "MmsSendSchedule"; // 任务进程的名称
	protected Date taskBeginTime = dateUtil.getToday(); // 本次任务处理开始时间
	protected int totalTasks = 10; // 本次要处理的任务总数
	protected int remainTasks = 0; // 本次尚未处理的任务数
	protected String currentTaskName = "订餐系统彩信下发任务调度"; // 当前正在处理的任务名称
	protected Date lastTaskBeginTime = null; // 上次任务处理开始时间
	protected String taskDescription = null; // 当前任务情况描述
	
	private SendScheduleService sendScheduleService;

	@Override
	public void process() {
		
		log.info("开始处理任务：" + scheduleName);
		/********* 记录任务信息 ************/
		this.setSchedulerName_1("MmsSendSchedule");
		this.setJobName(this.scheduleName);
		this.setTotalTasks(1); // 取得总任务数
		this.jobBegin();

		/********* 记录作业信息 ************/
		this.setCurrentTaskName("MmsSendScheduleService"); // 记录当前要处理的文件名
		this.setCurrentTaskDescription("彩信下发调度任务服务");
		this.setCurrentTaskRecords(1);
		this.taskBegin();
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			log.info("开始系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
			sendScheduleService.processSendSchedule();
			calendar.setTime(nowDate);
			log.info("结束系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
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
