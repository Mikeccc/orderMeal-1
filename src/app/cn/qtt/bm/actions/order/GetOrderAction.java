package app.cn.qtt.bm.actions.order;


import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.IUserMgr;
import app.cn.qtt.bm.manage.impl.OrderMgr;
import app.cn.qtt.bm.manage.impl.UserMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = "systemLogin", type="chain", params={"namespace","/system","actionName","login"} ),
    @Result(name = "competenceError", type="redirect", location="/error/competence-error.jsp" ),
    @Result(name = "getOrderError", type="redirect", location="/error/get-order-error.jsp" ),
    @Result(name = "over", type="dispatcher", location="/WEB-INF/content/order/over.jsp" ),
    @Result(name = "authCodeVervify", type="redirect", location="/WEB-INF/content/order/get-order.jsp" ),
    @Result(name = "authcodeVerifyView", location = "/WEB-INF/content/order/authcode-verify.jsp"),
    @Result(name = "shopError", type="redirect", location="/error/shop-error.jsp" )
})
public class GetOrderAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	

	private OrderMgr orderMgr;

	private Integer orderId;
	private Integer shopId;
	
	private TSysUserInfo sysUserInfo; 
	
	private OrderResponseBean orderResponseBean;
	private List list;
	
	private String to;
	
	private String tel;
	
	private IUserMgr userMgr;
	
	private String authcode;
	
	private String mdn;
	
	public String execute(){
		final String xFunctionName  = "GetOrderAction.execute()";
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
				HttpServletRequest request = super.getRequest();
				String queryString = request.getQueryString();
				String requestURL = request.getRequestURL().toString();
				//用户请求的参数
				to = URLEncoder.encode(StringUtils.isNotBlank(queryString) ? (requestURL + "?" + queryString) : requestURL, "UTF-8");
				super.getRequest().setAttribute("to", this.to);
				return "systemLogin";//系统登录页
			}
			
			
			if (null == sessionUserInfo.getShopInfo()
					|| null == sessionUserInfo.getShopInfo().getShopId()
					|| !sessionUserInfo.getShopInfo().getShopId().equals(shopId)) {
				
				return "competenceError";
			}
			
			
			MyOrder order = new MyOrder();
			order.setOrderId(orderId);
			order.setShopId(shopId);
			orderResponseBean =orderMgr.selectOrderGoodsByOrderIdAndShopId(order);
			list = orderResponseBean.getResultList();//存放订单店铺及店铺对应的商品
			
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return SUCCESS;
	}
	
	
	public String modifyStatus(){
		final String xFunctionName  = "modifyStatus.execute()";
		String resultFlag = "";
		try{
			boolean modifyResult = false;
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			
			if (null == sessionUserInfo || null == sessionUserInfo.getUserInfo()||
					null == sessionUserInfo.getShopInfo()
					|| null == sessionUserInfo.getShopInfo().getShopId()
					|| !sessionUserInfo.getShopInfo().getShopId().equals(shopId)) {
				
				return "competenceError";
			}
			
			TSysUserInfo user = new TSysUserInfo();
			user.setUserPhoneNumber(tel);
			List<TSysUserInfo> userList = userMgr.queryUserByUserProperties(user);
			if(CollectionUtils.isNotEmpty(userList)){
				sysUserInfo = userList.get(0);
				
				TOrderInfo orderInfo = new TOrderInfo();
				orderInfo.setOrderId(orderId);
//				orderInfo.setOrderRunStatus("04");//订单运行状态00-预订购 01-已订购 02-发送中 03-发送完成 04-已领取 05-取消 06-过期
				orderInfo.setModifyUserCode(sysUserInfo.getUserCode());
				String orderStatus = orderMgr.selectOrderStatus(orderId);
				if(StringUtils.isNotBlank(orderStatus)){
					if("03".equals(orderStatus)){
						 modifyResult = orderMgr.modifyOrderRunStatus(orderInfo, sysUserInfo,shopId);

					}else if("04".equals(orderStatus)){
						resultFlag = "over";
					}
					if(modifyResult){
						resultFlag = "over";
					}else{
						resultFlag = "getOrderError";
					}
				}
			}else{
				resultFlag = "getOrderError";
			}
			
			
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	/**
	 * 
	 * 进入验证码验证页面，真实页面
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-9-23 下午5:28:55
	 */
	
	public String getAuthcodeVerify(){
		final String xFunctionName  = "getAuthcodeVerify()";
		gLogger.begin(xFunctionName);
		try{
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return "authcodeVerifyView";
	}
	/**
	 * 
	 * 通过手机号前四位和验证码验证
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-9-24 上午11:01:41
	 */
	public String skipToVerifyByAuthcode(){
		final String xFunctionName  = "GetOrderAction.execute()";
		gLogger.begin(xFunctionName);
		try{
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()||null == sessionUserInfo.getShopInfo().getShopId()){
				return "systemLogin";//系统登录页
			}
			
			shopId = sessionUserInfo.getShopInfo().getShopId();
			
			
			orderResponseBean =orderMgr.selectOrderGoodsByMdnAndAuthcode(mdn,authcode);
			if(orderResponseBean.getResultList()!=null&&orderResponseBean.getResultList().size()>0){
				TOrderInfoShop orderInfoShop = (TOrderInfoShop) orderResponseBean.getResultList().get(0);
				if(orderInfoShop!=null){
					if(shopId.intValue()!=orderInfoShop.getShopId().intValue()){
						return "shopError";
					}
					MyOrder order = new MyOrder();
					order.setOrderId(orderInfoShop.getOrderId());
					order.setShopId(orderInfoShop.getShopId());
					orderId = orderInfoShop.getOrderId();
					if(StringUtils.isNotBlank(orderInfoShop.getCreateUserCode())){
						TSysUserInfo sysUserInfo = userMgr.findUserByUserCode(orderInfoShop.getCreateUserCode());
						if(sysUserInfo!=null){
							tel = sysUserInfo.getUserPhoneNumber();
						}
					}
					orderResponseBean =orderMgr.selectOrderGoodsByOrderIdAndShopId(order);
					list = orderResponseBean.getResultList();//存放订单店铺及店铺对应的商品
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}


	public TSysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	public void setSysUserInfo(TSysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public OrderResponseBean getOrderResponseBean() {
		return orderResponseBean;
	}


	public void setOrderResponseBean(OrderResponseBean orderResponseBean) {
		this.orderResponseBean = orderResponseBean;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public String getTo() {
	
		return to;
	}


	public void setTo(String to) {
	
		this.to = to;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public IUserMgr getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(IUserMgr userMgr) {
		this.userMgr = userMgr;
	}


	public String getAuthcode() {
		return authcode;
	}


	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}


	public String getMdn() {
		return mdn;
	}


	public void setMdn(String mdn) {
		this.mdn = mdn;
	}


	
	

}
