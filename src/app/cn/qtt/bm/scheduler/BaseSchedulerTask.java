package app.cn.qtt.bm.scheduler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.UnableToInterruptJobException;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;

/**
 * <p>
 * Title: 全天通彩漫系统_调度系统
 * </p>
 * 
 * <p>
 * Description: 全天通彩漫系统2012年改造
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * <p>
 * Company: 北京全天通（www.qtt.cn）
 * </p>
 * 
 * @author zhengyi
 * @version 1.0
 */
public abstract class BaseSchedulerTask implements org.quartz.InterruptableJob {
	private static CCrppLog4j log = new CCrppLog4j(Scheduler.class.getName());

	private static final int STATUS_JOB_BEGIN = 1;
	private static final int STATUS_JOB_FINISHED = 2;
	private static final int STATUS_TASK_BEGIN = 3;
	private static final int STATUS_TASK_FINISHED = 4;

	public static final String TASK_STATE_SUCCESS = "成功";
	public static final String TASK_STATE_FAILED = "失败";

	private String schedulerName; // 该任务进程在配置文件中配置的名称

	private String schedulerName_1;

	private DateUtil dateUtil = new DateUtil();
//	private DBManager db = null;

	/** 当前工作线程属性 */
	private String hostIP; // 主机IP
	private String jobName; // 业务进程名称
	private int totalTasks = 0; // 本次任务要处理的作业总数
	private int finishedTasks = 0; // 本次任务尚未处理的作业数
	private String jobBeginTime; // 本次任务处理开始时间
	private String lastTaskFinishTime; // 本次工作处理结束时间

	/** 当前作业相关属性 */
	private String currentTaskName = ""; // 当前正在处理的作业名称
	private String currentTaskDescription; // 当前作业说明
	private int currentTaskRecords; // 当前作业记录数
	private String currentTaskBeginTime; // 当前作业处理开始时间
	private String currentTaskFinisheTime; // 本次作业处理结束时间
	private String currentTaskState; // 当前作业处理状态
	private String currentTaskStateDescription; // 当前作业处理状态说明

	private long totalMemory = 0; // 总内存
	private long freeMemory = 0; // 空闲内存

	/**
	 * 业务功能执行入口
	 * 
	 * @todo: 在配置文件中，targetMethod改成这个方法，如下： <property name="targetMethod"
	 *        value="perform"/>
	 */
	public void perform() {
		log.info("任务处理线程[" + this.schedulerName
				+ "]正在运行（Job thread is alive）...");
		process();
	}

	/**
	 * 任务处理入口方法
	 */
	public abstract void process();

	/**
	 * 销毁当前处理线程
	 */
	public void destory() {
		// log.info("Task ["+this.getCurrentTaskName()+"] destroied");
		// log.info("Thread name: "+Thread.currentThread().getName()) ;
		// Thread.currentThread().destroy();
	}

	/**
	 * 初始化数据库连接
	 * 
	 * @throws SchedulerException
	 */
	private void initDBManager() throws SchedulerException {

	}

	/**
	 * 保存任务处理日志
	 * 
	 * @todo: 在此完成明细日志的记录工作
	 */
	private void saveOrUpdateProcessLog(int stateFlag) {
		try {
			initDBManager();
		} catch (SchedulerException ex) {
			log.exception(".saveOrUpdateProcessLog", ex);
			return;
		}

		Connection conn = null;
		try {
			switch (stateFlag) {
			case STATUS_JOB_BEGIN:
				this.saveJobProcessLog(conn);
				break;
			case STATUS_JOB_FINISHED:
				this.saveJobProcessLog(conn);
				break;
			case STATUS_TASK_BEGIN:
				this.saveTaskProcessLog(conn);
				break;
			case STATUS_TASK_FINISHED:
				this.saveTaskProcessLog(conn);
				this.saveJobProcessLog(conn);
				break;
			default:
				break;
			}
		} catch (SchedulerException ex) {
			log.exception(".saveOrUpdateProcessLog 保存后台进程业务处理日志失败！", ex);
		} finally {
			if (conn != null) {
//				db.closeConnection(conn);
			}
		}
	}

	/**
	 * 保存任务处理日志
	 * 
	 * @param jobName
	 *            String
	 * @param jobObject
	 *            BaseSchedulerTask
	 * @param RWZT
	 *            String
	 * @param CLJG
	 *            String
	 * @param passedTime
	 *            long
	 * @throws SchedulerException
	 */
	private void saveJobProcessLog(Connection conn) throws SchedulerException {

	}

	/**
	 * 保存作业处理流水日志
	 * 
	 * @param conn
	 *            Connection
	 * @throws SchedulerException
	 */
	private void saveTaskProcessLog(Connection conn) throws SchedulerException {

	}

	/**
	 * 本次工作开始
	 */
	public void jobBegin() {
		this.setJobBeginTime(dateUtil.getCurTime());
		this.setFinishedTasks(0);
		this.totalMemory = Runtime.getRuntime().totalMemory();
		this.freeMemory = Runtime.getRuntime().freeMemory();
		saveOrUpdateProcessLog(BaseSchedulerTask.STATUS_JOB_BEGIN);
	}

	/**
	 * 本次工作结束
	 */
	public void jobFinished() {
		this.freeMemory = Runtime.getRuntime().freeMemory();
		saveOrUpdateProcessLog(BaseSchedulerTask.STATUS_JOB_FINISHED);

	}

	/**
	 * 任务开始
	 */
	public void taskBegin() {
		this.setCurrentTaskBeginTime(dateUtil.getCurTime());
		this.freeMemory = Runtime.getRuntime().freeMemory();
		saveOrUpdateProcessLog(BaseSchedulerTask.STATUS_TASK_BEGIN);
	}

	/**
	 * 任务结束
	 */
	public void taskFinished() {
		this.setCurrentTaskFinisheTime(dateUtil.getCurTime());
		this.setLastTaskFinishTime(this.getCurrentTaskFinisheTime());
		this.setTaskGuage();
		this.freeMemory = Runtime.getRuntime().freeMemory();
		saveOrUpdateProcessLog(BaseSchedulerTask.STATUS_TASK_FINISHED);
	}

	/**
	 * 更新最后作业描述
	 */

	public void updateZhzyms(String msg) {

	}

	/**
	 * 更新任务状态以及配置文件名
	 */
	public void updateJob(String rwzt) {
	}

	/**
	 * 判断是否挂起
	 */
	public boolean isSuspended() {
		return false;
	}

	public void interrupt() throws UnableToInterruptJobException {
	}

	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// this.setJobExecutionContext(jobExecutionContext);
		// log.info( "任务处理线程[" + this.schedulerName + "]正在运行（Job thread is
		// alive）..." );
	}

	/** 主机IP */
	public String getHostIP() {
		if (hostIP == null) {
			try {
				hostIP = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception ex) {
				log.debug(ex.getMessage());
				try {
					hostIP = InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException ex1) {
					log.debug(ex1.getMessage());
				}
			}
		}
		return hostIP;
	}

	/** 当前总内存 */
	public long getTotalMemory() {
		return totalMemory;
	}

	/** 当前工作任务名称 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	/** 本次任务开始时间 */
	private void setJobBeginTime(String jobBeginTime) {
		this.jobBeginTime = jobBeginTime;
	}

	public String getJobBeginTime() {
		if (this.jobBeginTime == null)
			this.jobBeginTime = "0000-00-00 00:00:00";
		return jobBeginTime;
	}

	/** 本次工作要处理的所有作业数 */
	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getTotalTasks() {
		if (this.totalTasks < 1)
			totalTasks = 0;
		return totalTasks;
	}

	/** 已完成作业数 */
	private void setFinishedTasks(int finishedTasks) {
		this.finishedTasks = finishedTasks;
	}

	public int getFinishedTasks() {
		return finishedTasks;
	}

	/** 取得剩余未处理作业数 */
	public int getRemainTasks() {
		return (this.totalTasks - this.finishedTasks);
	}

	/** 当前作业名称 */
	public void setCurrentTaskName(String currentTaskName) {
		this.currentTaskName = currentTaskName;
	}

	public String getCurrentTaskName() {
		return currentTaskName;
	}

	/**
	 * 当前作业描述，用以指明当前任务正在处理什么数据.
	 * 
	 * @return String
	 */
	public void setCurrentTaskDescription(String currentTaskDescription) {
		this.currentTaskDescription = currentTaskDescription;
	}

	public String getCurrentTaskDescription() {
		return currentTaskDescription;
	}

	/** 当前作业开始时间 */
	private void setCurrentTaskBeginTime(String currentTaskBeginTime) {
		this.currentTaskBeginTime = currentTaskBeginTime;
	}

	public String getCurrentTaskBeginTime() {
		return currentTaskBeginTime;
	}

	/** 当前作业结束时间 */
	private void setCurrentTaskFinisheTime(String currentTaskFinisheTime) {
		this.currentTaskFinisheTime = currentTaskFinisheTime;
	}

	public String getCurrentTaskFinisheTime() {
		return currentTaskFinisheTime;
	}

	/** 当前作业记录数 */
	public void setCurrentTaskRecords(int currentTaskRecords) {
		this.currentTaskRecords = currentTaskRecords;
	}

	public int getCurrentTaskRecords() {
		return currentTaskRecords;
	}

	/** 当前作业执行状态 */
	public void setCurrentTaskState(String currentTaskState) {
		this.currentTaskState = currentTaskState;
	}

	public String getCurrentTaskState() {
		return currentTaskState;
	}

	/** 最后作业完成时间 */
	public void setLastTaskFinishTime(String lastTaskFinishTime) {
		this.lastTaskFinishTime = lastTaskFinishTime;
	}

	public String getLastTaskFinishTime() {
		return lastTaskFinishTime;
	}

	/** 当前作业完成情况说明 */
	public void setCurrentTaskStateDescription(
			String currentTaskStateDescription) {
		this.currentTaskStateDescription = currentTaskStateDescription;
	}

	public String getCurrentTaskStateDescription() {
		return currentTaskStateDescription;
	}

	/**
	 * 设置进度指示，对已完成数加一
	 */
	private void setTaskGuage() {
		this.finishedTasks++;
	}

	/** 当前任务进行在配置文件中的名字 */
	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public String getSchedulerName_1() {
		return schedulerName_1;
	}

	public void setSchedulerName_1(String schedulerName_1) {
		this.schedulerName_1 = schedulerName_1;
	}

	/**
	 * @return the jobExecutionContext
	 */
	// public JobExecutionContext getJobExecutionContext() {
	// return jobExecutionContext;
	// }
	/**
	 * @param jobExecutionContext
	 *            the jobExecutionContext to set
	 */
	// public void setJobExecutionContext(JobExecutionContext
	// jobExecutionContext) {
	// this.jobExecutionContext = jobExecutionContext;
	// }
}
