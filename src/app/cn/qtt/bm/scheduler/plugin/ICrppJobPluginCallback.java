package app.cn.qtt.bm.scheduler.plugin;

/**
 * 任务插件回调接口定义
 * 
 * @author Administrator
 *
 */
public interface ICrppJobPluginCallback {
	/**
	 * 任务回调
	 * 
	 * @param pluginRefId 插件引用ID
	 * @param runResult 插件运行结果
	 */
	public void pluginCallback( Integer pluginRefId, String runResult );
}
