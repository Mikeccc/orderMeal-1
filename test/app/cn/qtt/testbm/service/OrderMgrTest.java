package app.cn.qtt.testbm.service;

import org.junit.Test;

import app.cn.qtt.bm.manage.IOrderMgr;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.testbm.base.BaseTest;

public class OrderMgrTest extends BaseTest {
	
	private IOrderMgr orderMgr;
	
	@Test
	public void addShopCartTest(){
		orderMgr = (IOrderMgr)getContext().getBean("orderMgr");
		CommonRequestBean requestBean= new CommonRequestBean();

	}


}
