package app.cn.qtt.bm.scheduler;

import java.net.InetAddress;
import java.util.HashMap;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import app.cn.qtt.bm.common.log.CCrppLog4j;

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
public class TaskMonitor extends BaseSchedulerTask {
	private static CCrppLog4j log = new CCrppLog4j(TaskMonitor.class.getName());
	/**
	 * 任务状态名称
	 */
	public static final String RWZT_ZC = "正常";
	public static final String RWZT_CS = "超时";
	public static HashMap hasStateLogJobMap = new HashMap();

	/**
	 * 对异常任务的处理方式
	 */
	public static final String CLJG_NOP = "正常运行";
	public static final String CLJG_ZZ = "终止线程让线程自动重启";
	public static String hostIP = null; // 主机IP地址

	/**
	 * 业务功能执行入口
	 * 
	 * @todo: 在配置文件中，targetMethod改成这个方法，如下： <property name="targetMethod"
	 *        value="perform"/>
	 */
	public void perform() {
		process();
	}

	/**
	 * 处理入口方法
	 * 
	 * @todo: 要网上查一下Scheduler的JobListener有什么用
	 */
	public void process() {
		if (this.scheduler == null) {
			log.error("The scheduler is null, please init scheduler first.");
			return;
		}

		// 循环检测各定时任务的状态
		try {
			checkJobTriggerState();
		} catch (Exception ex) {
			log.exception("检测任务处理线程状态失败。(Check Job trigger state failed.)", ex);
		}
	}

	/**
	 * 名称取得JobDetail
	 * 
	 * @param jobName
	 *            String
	 * @param jobGroupName
	 *            String
	 * @return JobDetail
	 */
	private JobDetail getJob(String jobName, String jobGroupName)
			throws SchedulerException {
		JobDetail jobDetail = scheduler.getJobDetail(jobName, jobGroupName);
		return jobDetail;
	}

	/**
	 * 取得TargetObject
	 * 
	 * @param jobDetail
	 *            JobDetail
	 * @return Object
	 */
	private Object getTargetObject(JobDetail jobDetail) {
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		MethodInvokingJobDetailFactoryBean factoryBean = (MethodInvokingJobDetailFactoryBean) jobDataMap
				.get("methodInvoker");
		Object targetObject = factoryBean.getTargetObject();
		return targetObject;
	}

	/**
	 * 检测每个Trigger的运行状态
	 */
	private void checkJobTriggerState() throws SchedulerException {
		String[] jobNames = null;
		try {
			jobNames = scheduler.getJobNames(StdScheduler.DEFAULT_GROUP);
			for (int ii = 0; ii < jobNames.length; ii++) {
				Trigger[] triggerArray = null;
				try {
					triggerArray = scheduler.getTriggersOfJob(jobNames[ii],
							StdScheduler.DEFAULT_GROUP);
					for (int jj = 0; jj < triggerArray.length; jj++) {
						int state = scheduler.getTriggerState(triggerArray[jj]
								.getName(), triggerArray[jj].getGroup());
						log.info(">>>> Job [" + jobNames[ii] + "] "
								+ getTriggerStateName(state));
					}

					JobDetail jobDetail = getJob(jobNames[ii],
							StdScheduler.DEFAULT_GROUP);
					Object targetObject = getTargetObject(jobDetail);
					if (targetObject instanceof BaseSchedulerTask) {

					} else {
						log.error("The task [" + jobDetail.getName()
								+ " ] isn't a instance of BaseSchedulerTask!");
					}
				} catch (SchedulerException ex) {
					throw ex;
				}
			}
			if (jobNames.length == 1) {
				if (jobNames[0].equals(this.getSchedulerName())
						&& !Scheduler.isKeepRun()) {
					log
							.info("所有业务处理线程都已退出，任务调度主程序正在停止，请查看操作系统进程队列以确认是否已停止。(All jod stopped, main scheduler is stopping now.)");
					System.exit(0);
				}
			}
		} catch (SchedulerException ex) {
			throw ex;
		}
	}

	/**
	 * 保存任务状态
	 * 
	 * @param jobName
	 *            String
	 * @param jobObject
	 *            BaseSchedulerTask
	 * @param RWZT
	 *            String
	 * @param CLJG
	 *            String
	 */
	private void saveTaskState(String jobName, BaseSchedulerTask jobObject,
			String RWZT, String CLJG, long passedTime)
			throws SchedulerException {
	}

	/**
	 * 根据Trigger的状态代码取得状态名称
	 * 
	 * @param triggerState
	 *            int Trigger状态代码
	 * @return String
	 */
	private String getTriggerStateName(int triggerState) {
		String triggerStateName = "not exists.";
		switch (triggerState) {
		case Trigger.STATE_NORMAL:
			triggerStateName = "is running...";
			break;
		case Trigger.STATE_PAUSED:
			triggerStateName = "has paused.";
			break;
		case Trigger.STATE_COMPLETE: // --> Has no remaining fire times in its
			// schedule:
			triggerStateName = "has complete.";
			break;
		case Trigger.STATE_ERROR: // --> In error state (won't be fired)
			triggerStateName = "error.";
			break;
		case Trigger.STATE_BLOCKED: // --> When a stateful job is currently
			// executing
			triggerStateName = "stand by.";
			break;
		case Trigger.STATE_NONE: // --> The trigger doe not exist
			triggerStateName = "not exists.";
			break;
		}
		return triggerStateName;
	}

	/**
	 * 单个任务允许的最大处理时间，以秒为单位
	 */
	private int maxTaskTime = 2 * 60 * 60;

	public int getMaxTaskTime() {
		return maxTaskTime;
	}

	public void setMaxTaskTime(int maxTaskTime) {
		this.maxTaskTime = maxTaskTime;
	}

	/**
	 * 监视检测时间间隔
	 */
	private int checkInterval = 1;

	public int getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(int checkInterval) {
		this.checkInterval = checkInterval;
	}

	/**
	 * 要监控的Scheduler
	 */
	private StdScheduler scheduler = null;

	public StdScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(StdScheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * 取得本机的IP地址
	 * 
	 * @return String
	 */
	public String getHostIP() {
		if (hostIP == null) {
			try {
				hostIP = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception ex) {
				log.debug(ex.getMessage());
			}
		}
		return hostIP;
	}

	public String getId() {
		return new StringBuffer(getClass().getName()).append(
				"#hasStateLogJobMap").toString();
	}

	public Object getLinkObject() {
		return hasStateLogJobMap;
	}

	public String getName() {
		return getId();
	}

	public long getValue() {
		return (long) ((hasStateLogJobMap == null) ? 0 : hasStateLogJobMap
				.size());
	}
	
}
