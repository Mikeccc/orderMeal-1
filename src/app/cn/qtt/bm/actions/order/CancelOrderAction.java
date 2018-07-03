package app.cn.qtt.bm.actions.order;



import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.impl.OrderMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = "addOrderError", location = "/WEB-INF/content/order/add-order-error.jsp",params={"errorMessage","${errorMessage}"}),
    @Result(name = "list", type="redirect", location="add-shoping-cart!list?type=3&orderId=${orderId}&shopId=${shopId}")
})
public class CancelOrderAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	

	private OrderMgr orderMgr;
	
	private TOrderInfo orderInfo; 
	private TSysUserInfo sysUserInfo; 
	
	private OrderResponseBean orderResponseBean;
	private Integer orderId;
	private Integer shopId;
	
	public String execute(){
		final String xFunctionName  = "CancelOrderAction.execute()";
		
		String resultFlag = "addOrderError";
		try{
			gLogger.begin(xFunctionName);
			
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
					return LOGIN;
			}
			
			String startTimeParameter = CacheConstants.BM_START_TIME();
			String overTimeParameter = CacheConstants.getParamValueByName("over_time");
            if(StringUtils.isEmpty(overTimeParameter)){
            	overTimeParameter="10:00:00";
            }
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
            Date startTime = sdf.parse(startTimeParameter);
            Date overTime = sdf.parse(overTimeParameter);
            Date nowTime = sdf.parse(sdf.format(new Date()));
            
			if(nowTime.before(overTime)&& nowTime.after(startTime)){
				
				TSysUserInfo  userInfo = sessionUserInfo.getUserInfo();
				String userCode = userInfo.getUserCode();
				if(null != userInfo && StringUtils.isNotBlank(userCode)){
					orderInfo = new TOrderInfo();
					orderInfo.setCreateUserCode(userCode);
				}
				resultFlag = SUCCESS;
			}else{
				this.errorMessage ="超过订餐时间,取消失败。";
				resultFlag = "addOrderError";
			}	
			
			
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	public String cancel(){
		final String xFunctionName  = "CancelOrderAction.cancel()";
		gLogger.begin(xFunctionName);
		String resultFlag = "addOrderError";
		try{
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
					return LOGIN;
			}
			
			String startTimeParameter = CacheConstants.BM_START_TIME();
			String overTimeParameter = CacheConstants.getParamValueByName("over_time");
            if(StringUtils.isEmpty(overTimeParameter)){
            	overTimeParameter="10:00:00";
            }
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
            Date startTime = sdf.parse(startTimeParameter);
            Date overTime = sdf.parse(overTimeParameter);
            Date nowTime = sdf.parse(sdf.format(new Date()));
            
			if(nowTime.before(overTime)&& nowTime.after(startTime)){
				
				TSysUserInfo  userInfo = sessionUserInfo.getUserInfo();
				orderInfo = new TOrderInfo();
				TOrderInfoShop orderInfoShop = new TOrderInfoShop();
				orderInfo.setCreateUserCode(userInfo.getUserCode());
				OrderResponseBean orderResponseBean = orderMgr.selectMyOrderGoods(orderInfo, orderInfoShop);
				List list = orderResponseBean.getResultList();
				if(CollectionUtils.isNotEmpty(list)){
					MyOrder order = (MyOrder)list.get(0);
					orderId = order.getOrderId();
				}
				orderInfo.setOrderId(orderId);
				orderMgr.modifyOrderCancelStatus(orderInfo, userInfo);
				resultFlag ="list";
			}else{
				this.errorMessage ="超过订餐时间,取消失败。";
				resultFlag = "addOrderError";
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			this.errorMessage =  "业务异常";
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	public OrderMgr getOrderMgr() {
		return orderMgr;
	}

	public void setOrderMgr(OrderMgr orderMgr) {
		this.orderMgr = orderMgr;
	}

	public TOrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(TOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}


	public TSysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	public void setSysUserInfo(TSysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}


	public OrderResponseBean getOrderResponseBean() {
		return orderResponseBean;
	}

	public void setOrderResponseBean(OrderResponseBean orderResponseBean) {
		this.orderResponseBean = orderResponseBean;
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	
	

}
