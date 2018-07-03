package app.cn.qtt.bm.scheduler.impl;

import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.scheduler.IOverTimeOrderQuartzJobBean;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

public class OverTimeOrderQuartzJobBean implements IOverTimeOrderQuartzJobBean {
	private CCrppLog4j gLogger = new CCrppLog4j(
			OverTimeOrderQuartzJobBean.class.getName());
	private IOrderService orderService;
	@Override
	public void startJob() {
		try{
			gLogger.begin("startJob");
			this.processJob();
		}catch(Exception e){
			gLogger.exception("startJob", e);
		}finally{
			gLogger.info("#######job 结束#######");
		}
		
	}
	@SuppressWarnings("unchecked")
	private void processJob() throws Exception {
		gLogger.info("取当天之前的未领取的订单");
		List<TOrderInfo> list; 
		OrderRequestBean requestBean=new OrderRequestBean();
		TOrderInfoExample orderInfoExample=new TOrderInfoExample();
		orderInfoExample.createCriteria().andCreateTimeLessThan(new Date()).andOrderRunStatusEqualTo(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_3));
		requestBean.setOrderInfoExample(orderInfoExample);
		OrderResponseBean responseBean=orderService.queryOrders(requestBean);
		if("00".equals(responseBean.getResponseCode())){
			list=(List<TOrderInfo>)responseBean.getResultList();
		}else{
			throw responseBean.getException();
		}
		if(CollectionUtils.isEmpty(list)){
			gLogger.info("没有符合的订单,退出本次job");
		}else{
			for(TOrderInfo orderInfo:list){
				orderInfo.setOrderRunStatus(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_6));
				OrderRequestBean requestBean2=new OrderRequestBean();
				requestBean2.setOrderInfo(orderInfo);
				responseBean=orderService.modifyOrderRunStatus(requestBean2);
				if("00".equals(responseBean.getResponseCode())){
					gLogger.info(orderInfo.getOrderCode()+"订单信息修改成功");
				}else{
					gLogger.info(orderInfo.getOrderCode()+"订单信息修改失败");
					throw responseBean.getException();
				}
				//修改订单店铺状态
				requestBean2=new OrderRequestBean();
				TOrderInfoShop orderInfoShop=new TOrderInfoShop();
				//orderInfoShop.setOrderId(orderInfo.getOrderId());
				orderInfoShop.setOrderShopRunStatus(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_6));
				requestBean2.setOrderInfoShop(orderInfoShop);
				TOrderInfoShopExample orderInfoShopExample=new TOrderInfoShopExample();
				orderInfoShopExample.createCriteria().andOrderIdEqualTo(orderInfo.getOrderId()).andOrderShopRunStatusNotEqualTo(Constants.ORDER_RUN_STATUS_4);
				requestBean2.setOrderInfoShopExample(orderInfoShopExample);
				responseBean=orderService.modifyOrderShopInfo(requestBean2);
				if("00".equals(responseBean.getResponseCode())){
					gLogger.info(orderInfo.getOrderCode()+"订单店铺状态修改成功");
				}else{
					gLogger.info(orderInfo.getOrderCode()+"订单店铺状态修改失败");
					throw responseBean.getException();
				}
			}
		}
	}
	public IOrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

}
