package app.cn.qtt.bm.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import app.cn.qtt.bm.common.init.ContextInit;
import app.cn.qtt.bm.common.init.ContextInitBeans;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.ContextUtil;




/**
 * 全局上下文环境初始化基类
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class GlobalContextInitServlet extends HttpServlet
{
	//log
	protected CCrppLog4j log = new CCrppLog4j(this.getClass().getName());

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		ServletContext context = config.getServletContext();
		this.initContext(context);
	}

	protected void initContext(ServletContext context) {
		log.info("###############  cqs application init start...  ###############");
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		// 设置Spring工具
		ContextUtil.setWebApplicationContext(ctx);
		// 设置容器的上下文
		ContextUtil.setServletContext(context);
		
		if(ctx.containsBean("contextInitBeans")){
			Object object = (ContextInitBeans) ctx.getBean("contextInitBeans");
			if (object != null) {
				if (object instanceof ContextInitBeans) {
					ContextInitBeans contextInitBeans = (ContextInitBeans) object;
					for (ContextInit init : contextInitBeans.getContextInitBeans()) {
						init.init(context);
					}
				}
			}
		}
		

		log.info("###############  cqs application init completed...  ###############");
	}

	@Override
	public void destroy() {
		ServletContext context = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		if(ctx.containsBean("contextInitBeans")){
			Object object = (ContextInitBeans) ctx.getBean("contextInitBeans");
			if (object != null) {
				if (object instanceof ContextInitBeans) {
					ContextInitBeans contextInitBeans = (ContextInitBeans) object;
					for (ContextInit init : contextInitBeans.getContextInitBeans()) {
						init.destroy();
					}
				}
			}
		}
		
		super.destroy();
	}
}
