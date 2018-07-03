package app.cn.qtt.bm.common.init;

import java.util.ArrayList;
import java.util.List;




public class ContextInitBeans
{

	/**
	 * 初始化对像集合
	 */
	private List<ContextInit> contextInitBeans;

	private List<String> initBeansClazz;

	/**
	 * 转换成对像
	 */
	public void init()
	{
		try
		{
			for (String clazz : initBeansClazz)
			{
				ContextInit init = (ContextInit) Class.forName(clazz).newInstance();
				this.contextInitBeans.add(init);
			}
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<ContextInit> getContextInitBeans()
	{
		if (null == contextInitBeans)
		{
			contextInitBeans = new ArrayList<ContextInit>();
			this.init();
		}
		return contextInitBeans;
	}

	public void setContextInitBeans(List<ContextInit> contextInitBeans)
	{
		this.contextInitBeans = contextInitBeans;
	}

	public List<String> getInitBeansClazz()
	{
		return initBeansClazz;
	}

	public void setInitBeansClazz(List<String> initBeansClazz)
	{
		this.initBeansClazz = initBeansClazz;
	}

}
