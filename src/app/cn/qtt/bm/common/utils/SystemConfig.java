package app.cn.qtt.bm.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import app.cn.qtt.bm.common.log.CCrppLog4j;


/**
 * <p>
 * Title: 全天通彩漫系统_properties读取工具类
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
public class SystemConfig {
	private static CCrppLog4j log = new CCrppLog4j(SystemConfig.class.getName());

	protected static final String CONFIG_FILE = "deploy";

	protected static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	protected static String JNDI_URL = "t3://127.0.0.1:80";

	protected static ResourceBundle resource = null; // 资源文件
	protected static Context context = null; // JNDI上下文

	/**
	 * 获取JNDI上下文
	 * 
	 * @return Context 上下文对象
	 * @throws Exception
	 */
	public synchronized Context getInitialContext() throws Exception {
		if (context != null) {
			context.close();
			// return context;
		}

		String JNDI_URL = parseParam("JNDI.URL", false);
		String JNDI_FACTORY = parseParam("JNDI.FACTORY", false);
		String JNDI_USER = parseParam("JNDI.USER", false);
		String JNDI_PASSWORD = parseParam("JNDI.PASSWD", false);

		Context context = null;
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
			properties.put(Context.PROVIDER_URL, JNDI_URL);
			if (JNDI_USER != null) {
				properties.put(Context.SECURITY_PRINCIPAL, JNDI_USER);
			}
			if (JNDI_PASSWORD != null) {
				properties.put(Context.SECURITY_CREDENTIALS, JNDI_PASSWORD);
			}
			context = new InitialContext(properties);
		} catch (NamingException ex) {
			throw new Exception(ex);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return context;

	}

	/**
	 * 读取数据源配置信息
	 * 
	 * @param boolean ifRefresh 是否要重新读取配置文件
	 */
	public void parseJNDIParams(boolean ifRefresh) {
		try {
			JNDI_URL = parseParam("JNDI_URL", false);
			JNDI_FACTORY = parseParam("JNDI_FACTORY", false);
		} catch (MissingResourceException ex) {
			log
					.exception(
							"Can not read the properties file; Make sure asconfig.properties is in the Classpath",
							ex);
			// 如果出现异常，则设置默认参数
			JNDI_URL = "t3://127.0.0.1:80";
			JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
		}
	}

	/**
	 * 按指定属性名称读取配置信息
	 * 
	 * @param paramName
	 *            要读取的参数名
	 * @param ifRefresh
	 *            是否要刷新读取
	 * @return String 返回读取到的属性值，如果没读到，则返回空值
	 */
	public static String parseParam(String paramName, boolean ifRefresh) {
		String ret = null;
		try {
			getResource(ifRefresh);
			List keyList = getKeyList(resource);
			if (keyList.contains(paramName)) {
				ret = resource.getString(paramName);
			} else {
				if (!"JNDI.USER".equalsIgnoreCase(paramName)
						&& !"JNDI.PASSWD".equalsIgnoreCase(paramName))
					log.error("Can't find property [" + paramName
							+ "] in config file [" + CONFIG_FILE
							+ ".properties].");
			}
		} catch (MissingResourceException ex) {
			log.error("Can't find property in config file [" + CONFIG_FILE
					+ ".properties].\n" + ex.getMessage());
			ret = null;
		} catch (IllegalArgumentException ex) {
			log.exception("Load properties and get param failed.", ex);
			ret = null;
		} catch (SecurityException ex) {
			log.exception("Load properties and get param failed.", ex);
			ret = null;
		}
		return ret;
	}

	/**
	 * 取得配置文件内容
	 * 
	 * @param ifRefresh
	 *            boolean 是否刷新标志
	 * @return ResourceBundle
	 */
	private static void getResource(boolean ifRefresh) {
		// 如果指定要刷新配置文件，则重新读取
		if (ifRefresh) {
			Class clazz = ResourceBundle.getBundle(CONFIG_FILE).getClass()
					.getSuperclass();
			Field field = null;
			try {
				field = clazz.getDeclaredField("cacheList");
			} catch (NoSuchFieldException ex) {
				log.exception("Reload properties failed.", ex);
			}
			if (field != null) {
				field.setAccessible(true);
				// SoftCache cache = null;
				ConcurrentHashMap tmpMap = null;
				try {
					tmpMap = (ConcurrentHashMap) field.get(null);
					tmpMap.clear();
				} catch (IllegalAccessException ex) {
					log.exception("Reload properties failed.", ex);
				} finally {
					if(tmpMap != null) {
					  tmpMap.clear();
					  tmpMap = null;
					}
				}
				field.setAccessible(false);
			}
			resource = ResourceBundle.getBundle(CONFIG_FILE);
		}
		if (resource == null) {
			resource = ResourceBundle.getBundle(CONFIG_FILE);
		}
		return;
	}

	/**
	 * 取得配置文件所有配置项
	 * 
	 * @param res
	 *            ResourceBundle
	 * @return List
	 */
	private static List getKeyList(ResourceBundle res) {
		Enumeration keys = res.getKeys();
		ArrayList keyList = new ArrayList();
		while (keys.hasMoreElements()) {
			keyList.add((String) keys.nextElement());
		}
		return keyList;
	}

	/**
	 * 到得boolean型的参数
	 * 
	 * @param paramName
	 *            String
	 * @param ifRefresh
	 *            boolean
	 * @return boolean
	 */
	public boolean parseBooleanParm(String paramName, boolean ifRefresh) {
		boolean ret = true;
		String param = parseParam(paramName, ifRefresh);
		if (param == null || "".equals(param.trim())) {
			ret = false;
		} else {
			if (param.equalsIgnoreCase("Y") || param.equalsIgnoreCase("TRUE")
					|| param.equalsIgnoreCase("1")
					|| param.equalsIgnoreCase("T")) {
				ret = true;
			} else if (param.equalsIgnoreCase("N")
					|| param.equalsIgnoreCase("FALSE")
					|| param.equalsIgnoreCase("0")
					|| param.equalsIgnoreCase("F")) {
				ret = false;
			}
		}
		return ret;
	}

	/**
	 * 得到double型的参数
	 * 
	 * @param paramName
	 *            String
	 * @param ifRefresh
	 *            boolean
	 * @return int
	 */
	public double parseDoubleParm(String paramName, boolean ifRefresh) {
		String param = parseParam(paramName, ifRefresh);
		if (param == null || "".equals(param.trim())) {
			param = "0";
		}
		return Double.parseDouble(param);
	}

	/**
	 * 得到integer型参数
	 * 
	 * @param paramName
	 *            String
	 * @param ifRefresh
	 *            boolean
	 * @return int
	 */
	public int parseIntegerParm(String paramName, boolean ifRefresh) {
		String param = parseParam(paramName, ifRefresh);
		if (param == null || "".equals(param.trim())) {
			param = "0";
		}
		return Integer.parseInt(param);
	}
}
