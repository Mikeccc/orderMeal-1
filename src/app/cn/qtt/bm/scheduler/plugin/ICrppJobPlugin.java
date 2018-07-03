package app.cn.qtt.bm.scheduler.plugin;



/**
 * 任务插件接口定义，主要定义一下功能：
 * 1、设置插件参数
 * 2、设置插件回调对象
 * 3、运行插件
 * 
 * @author Administrator
 *
 */
public interface ICrppJobPlugin extends Runnable {
	/**
	 * 设置插件回调对象
	 * 
	 * @param pluginRefId 插件引用ID
	 * @param callback 回调对象
	 * @throws CCrppException
	 */
	public void setPluginCallback( Integer pluginRefId );
	
	
	public boolean getPluginRunningToken( Integer pluginRunningId );
	
	public void finishPluginRunningTokenError( String result );
}
