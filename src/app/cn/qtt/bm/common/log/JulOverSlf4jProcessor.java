/**
 * 
 */
package app.cn.qtt.bm.common.log;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * 在Spring ApplicationContext中初始化Slf4对Java.util.logging的拦截.
 * 
 * @author jack
 */
public class JulOverSlf4jProcessor {

	// Spring在所有属性注入后自动执行的函数.
	public void init() {
		SLF4JBridgeHandler.install();
	}
}
