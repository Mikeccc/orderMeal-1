/**
 * 
 */
package app.cn.qtt.bm.scheduler;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.scheduler.plugin.ICrppJobPlugin;

/**
 * @author Gabriel
 * @Description
 * @date 2013-7-3 下午9:20:45
 * @type SmsPwdRstQuartzJobBean
 * @project BespeakMeal
 */
public class SmsPwdRstQuartzJobBean implements ISmsQuartzJobBean {
	private CCrppLog4j gLogger = new CCrppLog4j(
			SmsPwdRstQuartzJobBean.class.getName());

	private ThreadPoolTaskExecutor taskExecutor;

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	private List<ICrppJobPlugin> mojiJobPlugins;
	
	private Integer gPluginRunningId = 0;

	public void startJob() {
		final String FUNCTION_NAME = ".startJob";
		this.gLogger.begin(FUNCTION_NAME);
		try {
			for (int i = 0; this.mojiJobPlugins != null
					&& i < this.mojiJobPlugins.size(); i++) {
				this.executeMojiJob(this.mojiJobPlugins.get(i));
			}
		} catch (Exception e) {
			this.gLogger.exception("", e);
		} finally {
			this.gLogger.end(FUNCTION_NAME);
		}
	}

	private void executeMojiJob(ICrppJobPlugin mojiJobPlugin) {
		if (mojiJobPlugin != null) {
			if (mojiJobPlugin.getPluginRunningToken(this.gPluginRunningId) == true) {
				try {
					mojiJobPlugin.setPluginCallback((++this.gPluginRunningId));
					this.taskExecutor.execute(new Thread(mojiJobPlugin));
					this.gLogger.logger().info(
							"<mojiJobBean.{}> : start plugin successfully",
							this.gPluginRunningId);
				} catch (Exception e) {
					this.gLogger.exception("", e);
					this.gLogger.logger().info(
							"<mojiJobBean.{}> : start plugin failed",
							this.gPluginRunningId);
					mojiJobPlugin.finishPluginRunningTokenError("ok");// 处理异常
				}
			} else {
				this.gLogger.logger().info(
						"<mojiJobBean.{}> : plugin is still running",
						this.gPluginRunningId);
			}
		} else {
			this.gLogger.logger().info("<error> : get job bean failed ");
		}
	}

	static ApplicationContext gContext = null;

	protected static class ReadContext {
		public static ApplicationContext readContext() {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "applicationPlugin-bean.xml",
							"applicationContext-quartz.xml", "**/*beans.xml" });

			return context;
		}
	}

	class DbConnection {

	}

	public List<ICrppJobPlugin> getMojiJobPlugins() {
		return mojiJobPlugins;
	}

	public void setMojiJobPlugins(List<ICrppJobPlugin> mojiJobPlugins) {
		this.mojiJobPlugins = mojiJobPlugins;
	}
}
