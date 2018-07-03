package app.cn.qtt.bm.common.utils;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

public class ContextUtil
{

	/**
	 * Spring工具类
	 */
	public static WebApplicationContext webApplicationContext;

	/**
	 * 容器的上下文
	 */
	public static ServletContext servletContext;

	/**
	 * 取Spring配制文件中的Bean
	 * @param name bean 名称
	 * @return
	 */
	public static Object getBean(String name) {
		if(webApplicationContext != null){
			if(hasBean(name)){
				return webApplicationContext.getBean(name);
			}
		}
		return null;
	}

	
	
	/**
	* 方法名称:      hasBean  
	* 方法描述:      判断是否存在
	* @param name
	* @return        
	* @Author:      linch
	* @Create Date: 2013-3-27 上午11:13:49
	*/ 
	 
	public static boolean hasBean(String name) {
		if(webApplicationContext != null){
			return webApplicationContext.containsBean(name);
		}
		return false;
	}
	
	
	/**
	 * 取容器上下文中的内容
	 * 
	 * @param attribute
	 * @return
	 */
	public static Object getAttribute(String attributeKey) {
		
		if(servletContext != null){
			return servletContext.getAttribute(attributeKey);
		}
		return null;
	}

	/**
	 * 设置上下文内容
	 * 
	 * @param attributeKey
	 * @param object
	 */
	public static void setAttribute(String attributeKey, Object object) {
		if(servletContext != null){
			servletContext.setAttribute(attributeKey, object);
		}
	}

	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public static void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		ContextUtil.webApplicationContext = webApplicationContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		ContextUtil.servletContext = servletContext;
	}

}
