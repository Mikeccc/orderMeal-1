package app.cn.qtt.bm.actions.order;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.RandomUtils;
import app.cn.qtt.bm.common.utils.TwoDimensionCode;
import app.cn.qtt.bm.manage.impl.OrderMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = "addOrderError", location = "/WEB-INF/content/order/add-order-error.jsp",params={"errorMessage","${errorMessage}"}),
    @Result(name = "deleteOrderGoodsError", location = "/WEB-INF/content/order/add-order-error.jsp",params={"errorMessage","${errorMessage}"}),
    @Result(name = "shopCategoryGoodsList",type="redirectAction",location="shop-category-goods-list",params={"orderId","${orderId}","shopId","${shopId}"}),
    @Result(name = "list", type="redirect", location="add-shoping-cart!list?orderId=${orderId}&shopId=${shopId}"),
    @Result(name = "myOrder",type="redirectAction",location="add-shoping-cart",params={"orderId","${orderId}","shopId","${shopId}"}),
    @Result(name = "shopList",type="chain",params={"namespace","/order","actionName","shop-list"}),
    @Result(name = "cancelOrder",type="redirectAction",location="cancel-order",params={"orderId","${orderId}","shopId","${shopId}"})
})
public class AddShopingCartAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	

	private OrderMgr orderMgr;
	private Integer shopId;
	private Integer shopGoodsId;
	private String shopGoodsPrice;
	
	private TOrderInfo orderInfo; 
	private TOrderInfoShop orderInfoShop;
	private TSysUserInfo sysUserInfo; 
	private TOrderInfoGoods orderInfoGoods;
	
	private OrderResponseBean responseBean;
	private List<Map> list;
	private Integer orderId;
	
	private String errorMessage;
	
	private String type;
	
	public String execute(){
		final String xFunctionName  = "AddShopingCartAction.execute()";
		String resultFlag = null;
		boolean result = false;
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
				TOrderInfo tempOrderInfo = new TOrderInfo();
				tempOrderInfo.setCreateUserCode(userCode);
				TOrderInfoShop orderInfoShop = new TOrderInfoShop();
//				if(shopId != null){
//					orderInfoShop.setOrderShopId(shopId);
//				}
				
				OrderResponseBean orderResponseBean = orderMgr.selectMyOrderGoods(tempOrderInfo, orderInfoShop);
				List list2 = orderResponseBean.getResultList();
				Integer orderIdTemp=0;
				if(CollectionUtils.isNotEmpty(list2)){
						MyOrder order = (MyOrder)list2.get(0);
						orderIdTemp = order.getOrderId();
				}
				
				if(orderIdTemp != null && orderIdTemp !=0){
					String orderStatus = orderMgr.selectOrderStatus(orderIdTemp);
					//如果订单状态不为空且为已下单，跳转到取消页
					if(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_02 )
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_03 )){
						resultFlag = "cancelOrder";
						return resultFlag;
					}
				}
			
			//1:继续订购；2：下单
			if(StringUtils.isNotBlank(type)&& (StringUtils.equals(type, "1")||StringUtils.equals(type, "继续订购"))){
				return "shopCategoryGoodsList";
			}
			
			//orderId不为空的时候不执行订购，只执行查询
			if(null != orderId && null != shopId){
				orderInfoShop = new TOrderInfoShop();
				orderInfoShop.setShopId(shopId);
				
				if(null != shopGoodsId){
					orderInfoGoods = new TOrderInfoGoods();
					orderInfoGoods.setShopGoodsId(shopGoodsId);
					
					Map<String, Object> resultMap = orderMgr.addShoppingCart(orderInfo, orderInfoShop, sessionUserInfo.getUserInfo(), orderInfoGoods,shopGoodsPrice);
					result = (Boolean)resultMap.get("resultFlag");
					orderId = (Integer)resultMap.get("orderId");
					String orderStatus = orderMgr.selectOrderStatus(orderId);//查询订单状态
					if(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)){//如果订单状态为已下单
						String phoneNum = sessionUserInfo.getUserInfo().getUserPhoneNumber();
						String imagePath = generationQRCode(phoneNum,orderId,shopId);
						
						TOrderInfoShop shop = new TOrderInfoShop();
						shop.setOrderId(orderId);
						shop.setShopId(shopId);
						shop.setOrderShopQrcode(imagePath);
						shop.setModifyUserCode(sessionUserInfo.getUserInfo().getUserCode());
						shop.setModifyTime(new Date());
						orderMgr.updateOrderShop(shop);
					}
				}else{
					//删除完毕返回列表
					result = true;
				}
			}else{
				if(null != shopId){
					orderInfoShop = new TOrderInfoShop();
					orderInfoShop.setShopId(shopId);
					
					orderInfoGoods = new TOrderInfoGoods();
					orderInfoGoods.setShopGoodsId(shopGoodsId);
					
					Map<String, Object> resultMap = orderMgr.addShoppingCart(orderInfo, orderInfoShop, sessionUserInfo.getUserInfo(), orderInfoGoods,shopGoodsPrice);
					result = (Boolean)resultMap.get("resultFlag");
					orderId = (Integer)resultMap.get("orderId");
					String orderStatus = orderMgr.selectOrderStatus(orderId);//查询订单状态
					if(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)){//如果订单状态为已下单
						String phoneNum = sessionUserInfo.getUserInfo().getUserPhoneNumber();
						String imagePath = generationQRCode(phoneNum,orderId,shopId);
						
						TOrderInfoShop shop = new TOrderInfoShop();
						shop.setOrderId(orderId);
						shop.setShopId(shopId);
						shop.setOrderShopQrcode(imagePath);
						shop.setModifyUserCode(sessionUserInfo.getUserInfo().getUserCode());
						shop.setModifyTime(new Date());
						orderMgr.updateOrderShop(shop);
					}
				}else{
					result = true;
				}
				
			}
			}else{
				this.errorMessage =CacheConstants.getParamValueByName("over_time_message");
				result = false;
			}
			if(result){
//				resultFlag = SUCCESS;
//				orderInfo = new TOrderInfo();
//				orderInfo.setOrderId(orderId);
//				
//				responseBean = orderMgr.selectOrders(orderInfo);
//				list = responseBean.getResultListMap();//存放订单店铺及店铺对应的商品
//				if(CollectionUtils.isNotEmpty(list)){
//					orderInfo = responseBean.getOrderInfo();//存放订单总价格和数量
//				}
			}else{
				resultFlag = "addOrderError";
			}
			
		}catch(CMRollBackException cmr){
			gLogger.exception(xFunctionName, cmr);
			this.errorMessage =  cmr.getMessage();
			result = false;
		}catch(CMException cme){
			gLogger.exception(xFunctionName, cme);
			int code = cme.getCode();
			if(code == ExceptionConstants.ILLEGAL_ARGUMENT_CODE){
				this.errorMessage =  "参数异常！";
			}else if(code == ExceptionConstants.UNKNOWN_BUSINESS_CODE){
				this.errorMessage =  "未知业务异常！";
			}else if(code == 100000){
				this.errorMessage =  cme.getMessage();
			}
			result = false;
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			this.errorMessage =  "业务异常";
			result = false;
		}finally{
			if(result){
				resultFlag = "list";
			}else{
				resultFlag = "addOrderError";
			}
			gLogger.end(xFunctionName);
		}
		
		return resultFlag;
	}

	public String generationQRCode(String phoneNum,Integer orderId,Integer shopId){
		//=========================生成二维码====================================//
		String existPath = CacheConstants.QRCODE_IMG_EXIST_PATH();
		String systemPath = CacheConstants.BESPEAKMEAL_SYSTEM_PATH();
		DateUtil du = new DateUtil();
		String ss= du.dateToString(new Date())+"/";
		ss = ss.replace("-", "/");
		String mmsid = RandomUtils.getMmsId();
		File file=new File(existPath+ss);
		if(!file.exists()){
			file.mkdirs();
		}
		
		String imgPath = existPath+ss+mmsid+".png";//"e:/test/Michael_QRCode.png";
		
        String encoderContent = systemPath+"order/get-order?tel="+phoneNum+"&orderId="+orderId+"&shopId="+shopId;  //order/lc?tel=**shopId=**
        TwoDimensionCode handler = new TwoDimensionCode();  
        handler.encoderQRCode(encoderContent, imgPath, "png");
        
        return imgPath;
//	    qcCodePreviewPath=imgPath;
//        String separator = System.getProperties().getProperty("file.separator");
//		qcCodePreviewPath = imgPath.replace(existPath, previewPath).replace("\\", "/");
		
		//=========================生成二维码====================================//
	}
	
	public String list(){
		final String xFunctionName  = "AddShopingCartAction.list()";
		gLogger.begin(xFunctionName);
		String resultFlag = "addOrderError";
		try{
			boolean result = false;
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
				orderInfo = new TOrderInfo();
				TSysUserInfo  userInfo = sessionUserInfo.getUserInfo();
				orderInfo = new TOrderInfo();
				TOrderInfoShop orderInfoShop = new TOrderInfoShop();
				orderInfo.setCreateUserCode(userInfo.getUserCode());
				OrderResponseBean orderResponseBean = orderMgr.selectMyOrderGoods(orderInfo, orderInfoShop);
				List tempList = orderResponseBean.getResultList();
				if(CollectionUtils.isNotEmpty(tempList)){
					MyOrder order = (MyOrder)tempList.get(0);
					orderId = order.getOrderId();
					shopId = order.getShopId();
					
					orderInfo.setOrderId(orderId);
					responseBean = orderMgr.selectOrders(orderInfo);
					list = responseBean.getResultListMap();//存放订单店铺及店铺对应的商品
					if(CollectionUtils.isNotEmpty(list)){
						orderInfo = responseBean.getOrderInfo();//存放订单总价格和数量
					}
					
					result = true;
				}else{
					this.errorMessage =  "您还未订餐！";
				}
				
			}else{
				this.errorMessage =CacheConstants.getParamValueByName("over_time_message");
				result = false;
			}
			
			if(result){
				//取消订单页的“继续修改”不用判断订单状态位，因为进入改页面说明是已下单
//				if(StringUtils.isNotBlank(type)&& StringUtils.equals(type, "3")){
//					resultFlag = SUCCESS;
//				}else{
					//点"订购"后及"修改"进入时，需要判断
					String orderStatus = orderMgr.selectOrderStatus(orderId);
					//如果订单状态不为空且为已下单，跳转到取消页
					if(StringUtils.isNotBlank(orderStatus)&& 
							(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_02 )
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_03 ))){
						resultFlag = "cancelOrder";
					}else{
						resultFlag = SUCCESS;
					}
//				}
			}else{
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
	
	public String goOnAdd(){
		final String xFunctionName  = "AddShopingCartAction.goOnAdd()";
		gLogger.begin(xFunctionName);
		String resultFlag = "addOrderError";
		try{
			boolean result = false;
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
				TOrderInfo tempOrderInfo = new TOrderInfo();
				tempOrderInfo.setCreateUserCode(userCode);
				TOrderInfoShop orderInfoShop = new TOrderInfoShop();
//				if(shopId != null){
//					orderInfoShop.setOrderShopId(shopId);
//				}
				
				OrderResponseBean orderResponseBean = orderMgr.selectMyOrderGoods(tempOrderInfo, orderInfoShop);
				List list2 = orderResponseBean.getResultList();
				Integer orderIdTemp=0;
				if(CollectionUtils.isNotEmpty(list2)){
						MyOrder order = (MyOrder)list2.get(0);
						orderIdTemp = order.getOrderId();
						
						if(orderIdTemp != null && orderIdTemp !=0){
							String orderStatus = orderMgr.selectOrderStatus(orderIdTemp);
							//如果订单状态不为空且为已下单，跳转到取消页
							if(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)
									||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_02 )
									||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_03 )){
								resultFlag = "cancelOrder";
								return resultFlag;
							}else if(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_00)){
								resultFlag = "list";
								return resultFlag;
							}
						}
				}else{
					resultFlag = "shopList";
					return resultFlag;
				}
				
				
					
				
			}else{
				this.errorMessage =CacheConstants.getParamValueByName("over_time_message");
				result = false;
			}
			
			if(result){
				//取消订单页的“继续修改”不用判断订单状态位，因为进入改页面说明是已下单
//				if(StringUtils.isNotBlank(type)&& StringUtils.equals(type, "3")){
//					resultFlag = SUCCESS;
//				}else{
					//点"订购"后及"修改"进入时，需要判断
					String orderStatus = orderMgr.selectOrderStatus(orderId);
					//如果订单状态不为空且为已下单，跳转到取消页
					if(StringUtils.isNotBlank(orderStatus)&& 
							(StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_01)
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_02 )
							||StringUtils.equals(orderStatus, Constants.BUFFER_RUN_STATUS_03 ))){
						resultFlag = "cancelOrder";
					}else{
						resultFlag = SUCCESS;
					}
//				}
			}else{
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
	
	
	//(参数：订单id,商品id)
	public String deleteOrderGoods(){
		final String xFunctionName  = "deleteOrderGoods()";
		gLogger.begin(xFunctionName);
		String resultFlag = "deleteOrderGoodsError";
		boolean result = false;
		try{
			
			orderInfo = new TOrderInfo();
			orderInfo.setOrderId(orderId);
			orderInfoGoods = new TOrderInfoGoods();
			orderInfoGoods.setShopGoodsId(shopGoodsId);
			orderInfoGoods.setShopId(shopId);
			
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
					return LOGIN;
			}
			
			result = orderMgr.deleteOrderGoods(orderInfo, orderInfoGoods,sessionUserInfo.getUserInfo());
			
		}catch(CMRollBackException cmr){
			gLogger.exception(xFunctionName, cmr);
			this.errorMessage =  cmr.getMessage();
			result = false;
		}catch(CMException cme){
			gLogger.exception(xFunctionName, cme);
			int code = cme.getCode();
			if(code == ExceptionConstants.ILLEGAL_ARGUMENT_CODE){
				this.errorMessage =  "参数异常！";
			}else if(code == ExceptionConstants.UNKNOWN_BUSINESS_CODE){
				this.errorMessage =  "未知业务异常！";
			}else if(code == 300000){
				this.errorMessage =  cme.getMessage();
				result = false;
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			this.errorMessage =  "业务异常";
			result = false;
		}finally{
			if(result){
				resultFlag = "myOrder";
			}else{
				resultFlag = "deleteOrderGoodsError";
			}
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopGoodsId() {
		return shopGoodsId;
	}

	public void setShopGoodsId(Integer shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
	}

	public String getShopGoodsPrice() {
		return shopGoodsPrice;
	}

	public void setShopGoodsPrice(String shopGoodsPrice) {
		this.shopGoodsPrice = shopGoodsPrice;
	}

	public TOrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(TOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public TOrderInfoShop getOrderInfoShop() {
		return orderInfoShop;
	}

	public void setOrderInfoShop(TOrderInfoShop orderInfoShop) {
		this.orderInfoShop = orderInfoShop;
	}

	public TSysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	public void setSysUserInfo(TSysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}

	public TOrderInfoGoods getOrderInfoGoods() {
		return orderInfoGoods;
	}

	public void setOrderInfoGoods(TOrderInfoGoods orderInfoGoods) {
		this.orderInfoGoods = orderInfoGoods;
	}

	public OrderResponseBean getResponseBean() {
		return responseBean;
	}

	public void setResponseBean(OrderResponseBean responseBean) {
		this.responseBean = responseBean;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
