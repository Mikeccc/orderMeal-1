package app.cn.qtt.bm.actions.order;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

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
public class MyOrderAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	

	private OrderMgr orderMgr;
	
	private TOrderInfo orderInfo; 
	private TSysUserInfo sysUserInfo; 
	
	private OrderResponseBean orderResponseBean;
	private List<Map> list;
	private List list2;
	private List shopIdList;
	private Integer orderId;
	private Integer shopId;
	
	public String execute(){
		final String xFunctionName  = "myOrderAction.execute()";
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
					return LOGIN;
			}
			
			TSysUserInfo  userInfo = sessionUserInfo.getUserInfo();
			String userCode = userInfo.getUserCode();
			if(null != userInfo && StringUtils.isNotBlank(userCode)){
				orderInfo = new TOrderInfo();
				orderInfo.setCreateUserCode(userCode);
				
				TOrderInfoShop orderInfoShop = new TOrderInfoShop();
				if(shopId != null){
					orderInfoShop.setOrderShopId(shopId);
				}
				
				orderResponseBean = orderMgr.selectMyOrderGoods2(orderInfo, orderInfoShop);
				list2 = orderResponseBean.getResultList();
				if(CollectionUtils.isNotEmpty(list2)){
					if(null == orderId){
						MyOrder order = (MyOrder)list2.get(0);
						shopId = order.getShopId();
						orderId = order.getOrderId();
						
						orderInfoShop.setOrderShopId(shopId);
						orderResponseBean = orderMgr.selectMyOrderGoods2(orderInfo, orderInfoShop);
						list2 = orderResponseBean.getResultList();
					}
					
					TOrderInfo orderInfo2 = new TOrderInfo();
					orderInfo2.setOrderId(orderId);
					gLogger.info(">>>>> orderId <<<<<"+orderId);
					OrderResponseBean orderResponseBean2 = orderMgr.selectOrders(orderInfo2);
					list = orderResponseBean2.getResultListMap();//存放订单店铺及店铺对应的商品
					
					int size = list2.size()-1;
					MyOrder myOrder = (MyOrder)list2.get(size);
					String qrcodepath = myOrder.getOrderShopQrcode();
					if(StringUtils.isNotBlank(qrcodepath)){
						if((qrcodepath.indexOf(CacheConstants.TOP_FILE_PATH()) > -1)){
							String qrcodeAccessPath = qrcodepath.replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
							myOrder.setOrderShopQrcode(qrcodeAccessPath);
						}
						list2.set(size, myOrder);
					}
				}
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return SUCCESS;
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

	public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List getList2() {
		return list2;
	}


	public void setList2(List list2) {
		this.list2 = list2;
	}


	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	
	

}
