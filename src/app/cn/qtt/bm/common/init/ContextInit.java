package app.cn.qtt.bm.common.init;

import javax.servlet.ServletContext;


public interface ContextInit {

	/**
	 * 初始化工作
	 */
	public void init(ServletContext context);
	
	/**
	 * 销毁
	 */
	public void destroy();
}
