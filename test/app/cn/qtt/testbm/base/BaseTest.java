package app.cn.qtt.testbm.base;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import junit.framework.TestCase;


public abstract class BaseTest extends TestCase{
	
	protected CCrppLog4j log = new CCrppLog4j(this.getClass().getName());
	
	private static ApplicationContext context;
	
	
	private final String[] contextPaths= new String[]{"app/cn/qtt/testbm/config/order-context-beans-test.xml"};
	
	
	
	public void setUp() throws Exception {
		try{
			if(context == null){
				context = new ClassPathXmlApplicationContext(contextPaths);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	
	
	public void tearDown() throws Exception {
		
	}



	public static ApplicationContext getContext() {
		return context;
	}

}
