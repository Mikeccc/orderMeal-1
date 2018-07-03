package app.cn.qtt.bm.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.CachedData;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.StringUtil;
import app.cn.qtt.bm.common.utils.SystemConfig;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.service.impl.CommonServiceImpl;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;

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
public class Scheduler {
	private static CCrppLog4j log = new CCrppLog4j(Scheduler.class.getName());

	private static final int _checkStopInterval = 5; // 检测是否需要停止服务的时间间隔

	private static final String _runStateFilePostfix = "_run_state"; // 程序运行状态指示文件
	private static final String _runFlag = "start";
	private static final String _stopFlag = "stop";
	private static StdScheduler _scheduler = null;

	DateUtil du = new DateUtil();
	
	/**
	 * 定时任务配置文件
	 */
	private static String CONFIG_FILE = "app/cn/qtt/bm/config/Scheduling_send.xml";

	public Scheduler() {
		SystemConfig cfg = new SystemConfig();
		String config = cfg.parseParam("SCHEDULER.CONFIG_FILE", false);
		if (config != null && !"".equals(config.trim())) {
			CONFIG_FILE = config;
		}
		log.debug("CONFIG_FILE: " + CONFIG_FILE);
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 *            String[]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext act = new FileSystemXmlApplicationContext("classpath:app/cn/qtt/bm/config/Scheduling_send.xml");
		
		Scheduler sch = new Scheduler();
		CommonServiceImpl commonService = (CommonServiceImpl) act.getBean("commonServiceImpl");
		List<TSysParameter> list = null;
		try {
			CommonRequestBean requestBean = new CommonRequestBean();
			CommonResponseBean responseBean = commonService.getSysParameter(requestBean);

			if (!"00".equals(responseBean.getResponseCode())) {
				log.info("查询参数表出错");
			} else {
				list = responseBean.getList();
			}
		}catch(Exception e){
			
		}
		
		sch.doCache(CacheConstants.cache,list);
		
//		if (args.length > 0) {
//			if (_stopFlag.equalsIgnoreCase(args[0])) {
//				sch.stopServer();
//			} else if (_runFlag.equalsIgnoreCase(args[0])) {
//				// 设置启动标志
//				sch.startServer();
//				// 载入调度线程
//				sch.execute();
//				// 检测运行标志，如果指定停止服务，则shutdown
//				checkAndStop();
//			}
//		} else {
//			help();
//		}
	}

	/**
	 * 运行定时任务
	 */
	public void execute() {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { CONFIG_FILE });
		BeanFactory factory = (BeanFactory) appContext;

		/**
		 * 装载任务调度
		 */
		_scheduler = (StdScheduler) factory.getBean("schedulerFactoryBean");
		if (_scheduler == null) {
			log.error("初始化任务调度程序失败。");
			System.exit(0);
		}
		try {
			String[] jobNames = _scheduler.getJobNames(StdScheduler.DEFAULT_GROUP);
			for (int ii = 0; ii < jobNames.length; ii++) {
				log.debug("jobName: " + jobNames[ii]);
				JobDetail job = _scheduler.getJobDetail(jobNames[ii], StdScheduler.DEFAULT_GROUP);
				Object targetObj = ((MethodInvokingJobDetailFactoryBean) job.getJobDataMap().get("methodInvoker"))
						.getTargetObject();
				if (targetObj != null && targetObj instanceof BaseSchedulerTask) {
					((BaseSchedulerTask) targetObj).setSchedulerName(jobNames[ii]);
				}
			}
		} catch (SchedulerException ex) {
			log.exception("启动任务调试服务失败，错误原因如下(Start scheduler failed)：", ex);
			System.exit(0);
		}

		// 先暂停所有任务，等待装载缓存代码表
		try {
			_scheduler.pauseAll();
			JobDetail job = _scheduler.getJobDetail("taskMonitorScheduleJob", StdScheduler.DEFAULT_GROUP);
			((TaskMonitor) ((MethodInvokingJobDetailFactoryBean) job.getJobDataMap().get("methodInvoker"))
					.getTargetObject()).setScheduler(_scheduler);
		} catch (SchedulerException ex) {
			log.exception("启动任务调试服务失败，错误原因如下(Start scheduler failed): ", ex);
			System.exit(0);
		}

		/**
		 * 装载缓存代码表
		 */
		// CachedTableMgr cachedtableMgr = (CachedTableMgr) factory
		// .getBean("cachedTableMgr");
		// try {
		// cachedtableMgr.loadCodeTable();
		// } catch (Exception ex) {
		// log.exception("Load cached table failed. System will exit.", ex);
		// System.exit(0);
		// }

		// 重新恢复所有任务
		try {
			_scheduler.resumeAll();
		} catch (SchedulerException ex) {
			log.exception("", ex);
		}
	}

	/**
	 * 打开运行状态标志文件
	 * 
	 * @return File
	 */
	private static File getRunStateFile() {
		String thisName = System.getProperty("name");
		if (StringUtil.isEmpty(thisName)) {
			log.error("未指定服务名，无法启动。");
			help();
			System.exit(0);
		}

		String fileName = thisName + _runStateFilePostfix;
		File file = null;
		try {
			file = new File(fileName);
			if (!(file.exists() && file.isFile())) {
				file.createNewFile();
			}
		} catch (IOException ex) {
			log.exception("IOException: ", ex);
			log.error("创建运行状态文件[" + fileName
					+ "]失败，无法启动后台进程服务(Create run state file failed, the scheduler can't start.)。");
			return null;
		}
		if (file == null) {
			log.error("系统已停止!");
			System.exit(0);
		}
		return file;
	}

	/**
	 * 启动服务时，设置运行状态文件
	 */
	protected void startServer() {
		DateUtil du = new DateUtil();
		String currentTime = DateUtil.format(du.now(), "yyyyMMddHHmmssSSS");
		File file = getRunStateFile();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
			if (raf == null) {
				log.error("无法打开运行状态文件[" + file.getName() + "]，系统将停止。(Can't open the state file, system going to stop.)");
				return;
			}
			raf.writeBytes(_runFlag + "," + currentTime);
		} catch (IOException ex) {
			log.exception("", ex);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException ex) {
					log.exception("", ex);
				}
			}
		}
	}

	/**
	 * 在运行状态文件中设置停止标志
	 */
	protected void stopServer() {
		DateUtil du = new DateUtil();
		String currentTime = DateUtil.format(du.now(), "yyyyMMddHHmmssSSS");
		File file = getRunStateFile();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
			if (raf == null) {
				log.error("无法打开运行状态文件[" + file.getName() + "]，系统将停止。(Can't open the state file, system going to stop.)");
				return;
			}
			raf.writeBytes(_stopFlag + "," + currentTime);
			log.info("停止命令已发出，请注意查看任务调度日志，以确认系统是否已停止(Command for stop sent.)。");
		} catch (IOException ex) {
			log.exception("", ex);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException ex) {
					log.exception("", ex);
				}
			}
		}
	}

	/**
	 * 检测标志，如果指定了停止服务，则调用 StdScheduler.shutdown( bool waitForJobsToComplete )
	 * 
	 * @return
	 */
	public static void checkAndStop() {
		while (true) {
			log.debug("正在检测是否停止服务...");
			if (!isKeepRun()) {
				log.info("运行状态已被更改为非运行状态，系统正在停止。(State flag has changed to stop, system stopping now, please wait...)");
				_scheduler.shutdown(true);
				return;
			}
			try {
				Thread.sleep(_checkStopInterval * 1000);
			} catch (InterruptedException ex) {
				log.exception("", ex);
			}
		}
	}

	/**
	 * 判断运行状态标志是否是继续保持运行状态
	 * 
	 * @return boolean
	 */
	public static boolean isKeepRun() {
		String thisName = System.getProperty("name");
		if (StringUtil.isEmpty(thisName)) {
			thisName = Scheduler.class.getName();
		}
		String fileName = thisName + _runStateFilePostfix;
		File file = getRunStateFile();
		if (file == null) {
			log.error("未找到运行状态文件[" + fileName + "]，系统将停止。(State file not found, system going to stop.)");
			return false;
		}

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			if (raf == null) {
				log.error("无法读取运行状态文件[" + fileName + "]，系统将停止。(Can't open the state file, system going to stop.)");
				return false;
			}

			String str = raf.readLine();
			if (StringUtil.isEmpty(str)) {
				log.error("运行状态文件[" + fileName + "]是空的(不正常)，系统将停止。(State file is empty, system going to stop.)");
				return false;
			}
			// log.debug("运行状态文件内容(Content of state file): " + str);
			if (str.toLowerCase().indexOf(_runFlag) >= 0) {
				return true;
			} else {
				log.info("运行状态已被更改为非运行状态，系统将停止。(State flag has changed to stop, system going to stop.)");
			}
		} catch (FileNotFoundException ex) {
			log.exception("", ex);
			log.error("未找到运行状态文件[" + fileName + "]，系统将停止。(State file not found, system going to stop.)");
			return false;
		} catch (IOException ex) {
			log.exception("", ex);
			log.error("无法从运行状态文件[" + fileName + "]中读取运行状态标志，系统将停止。(Can't read state from file, system going to stop.)");
			return false;
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException ex) {
					log.exception("Close state file failed.", ex);
				}
			}
		}
		return false;
	}

	/**
	 * 显示简单帮助
	 * 
	 */
	private static void help() {
		StringBuffer sb = new StringBuffer("\r\n帮助：\r\n").append("  启动：\r\n")
				.append("      java -cp %CLASSPATH% -Dname=<Scheduler Name> app.cn.qtt.scheduler.Scheduler start \r\n")
				.append("        其中<Scheduler Name>为当前服务名字，不同进程该名字不能相同，否则会相互干扰；\r\n")
				.append("        参数start不能缺.\r\n\r\n").append("  停止：\r\n")
				.append("      java -cp %CLASSPATH% -Dname=<Scheduler Name> app.cn.qtt.scheduler.Scheduler stop \r\n")
				.append("        其中<Scheduler Name>为要停止的服务的名字；\r\n").append("        参数stop不能缺.\r\n\r\n");
		log.info(sb.toString());
	}

	public boolean UnSuspended(String jobname) throws SchedulerException {
		boolean res = false;
		try {
			_scheduler.resumeJob(jobname, _scheduler.DEFAULT_GROUP);
			res = true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			res = false;
			log.exception("解挂失败", e);
			throw e;
		}
		return res;
	}

	public boolean suspended(String jobname) {
		boolean res = false;
		if (_scheduler == null) {
			log.error("初始化任务调度程序失败。");
			// System.exit(0);
		} else {
			try {
				log.info("------pausetriggerstart" + jobname + "-------");
				_scheduler.pauseJob(jobname, _scheduler.DEFAULT_GROUP);
				log.info("------pausetriggerend-------");
				res = true;
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				res = false;
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public void doCache(CachedData cache,List<TSysParameter> paramList) {
		String xFunctionName = "doCache";
		log.begin(xFunctionName);
		try {
			log.info("-------do ParameterCache-------");
			if (CollectionUtils.isNotEmpty(paramList)) {
				List<Vector> cacheList = new ArrayList<Vector>(paramList.size());
				for (int i = 0, sizeNo = paramList.size(); i < sizeNo; i++) {
					cacheList = new ArrayList<Vector>(paramList.size());
					Vector<Object> row = new Vector<Object>();
					Object value = null;
					value = paramList.get(i).getParamValue();
					row.add(value);
					cacheList.add(row);
					cache.putCodeTable((paramList.get(i).getParamCode()), cacheList, "PARAMNAME", "PARAMVALUE");
				}
			}
		} catch (Exception e) {
			log.exception(xFunctionName, e);
		} finally {
			log.begin(xFunctionName);
		}
	}
	
}
