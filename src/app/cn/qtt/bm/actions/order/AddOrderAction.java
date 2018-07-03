package app.cn.qtt.bm.actions.order;


import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;


import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
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
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = "addOrderError", location = "/WEB-INF/content/order/add-order-error.jsp",params={"errorMessage","${errorMessage}"}),
    @Result(name = "success",location="/WEB-INF/content/order/add-order.jsp"),
    @Result(name = "orderLogin", type="chain", params={"namespace","/order","actionName","login"} ),
    @Result(name = "shopCategoryGoodsList",type="chain",params={"namespace","/order","actionName","shop-category-goods-list"}),
    @Result(name = "shopList",type="redirectAction",location="shop-list")
})
public class AddOrderAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	
	private TOrderInfo orderInfo,orderInfo2;
	private Integer orderId;
	private String orderGoodsId;
	private String shopIdGoodsIdCount;
	private String shopGoodsCount;
	private OrderMgr orderMgr;
	private OrderResponseBean orderResponseBean;
	private List<Map> list;
	private List list2;
	private Integer shopId;
	private String qcCodePreviewPath;
	
	private GoodsRequestBean goodsRequestBean;
	private GoodsResponseBean goodsResponseBean;
	private TSysFile sysFile;
	private IGoodsService goodsService;
	
	private String to;
	
	private String type;
	private String errorMessage;
	
	public String execute(){
		final String xFunctionName  = "AddOrderAction.execute()";
		String resultFlag = "addOrderError";
		boolean result  = false;
		Map<Object,Object> resultMap =  new HashMap<Object,Object>();
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
				
				HttpServletRequest request = super.getRequest();
				String queryString = request.getQueryString();
				String requestURL = request.getRequestURL().toString();
				//用户请求的参数
				to = URLEncoder.encode(StringUtils.isNotBlank(queryString) ? (requestURL + "?" + queryString) : requestURL, "UTF-8");
				super.getRequest().setAttribute("to", this.to);
				return "orderLogin";
			}
			
			
			
			
			orderInfo = new TOrderInfo();
			orderInfo.setOrderId(orderId);
			gLogger.info(">>>>> orderId===="+orderId);
			List orderGoodsIdList = new ArrayList();
			List shopIdList = new ArrayList();
			List goodsIdList = new ArrayList();
			List countList = new ArrayList();
			gLogger.info(">>>>> orderGoodsId <<<<<"+orderGoodsId);
			
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
				if(StringUtils.isNotBlank(orderGoodsId)){
					String[] orderGoodsIdArr = orderGoodsId.split(",");//t_order_info_goods表主键：order_goods_id.70, 71, 72  
					String[] shopIdGoodsIdCountArr = shopIdGoodsIdCount.split(",");//3_7_2, 3_8_5, 2_2_2
					String[] shopGoodsCountArr = shopGoodsCount.split(",");//2, 5, 3
					for (int i = 0; i < shopGoodsCountArr.length; i++) {
						String tempCount =shopGoodsCountArr[i].trim();
						if(!StringUtils.isNumeric(tempCount)){
							resultFlag = "addOrderError";
						}
					}
					
					for (int i = 0; i < shopIdGoodsIdCountArr.length; i++) {
						String[] temp = shopIdGoodsIdCountArr[i].split("_");
						shopId =Integer.parseInt(temp[0].trim());
						Integer goodsId =Integer.parseInt(temp[1].trim());
						shopIdList.add(shopId);
						goodsIdList.add(goodsId);
						countList.add(shopGoodsCountArr[i].trim());
						orderGoodsIdList.add(orderGoodsIdArr[i].trim());
					}
					
					List<TOrderInfoGoods> orderInfoGoodsList = new ArrayList<TOrderInfoGoods>();
					TOrderInfoGoods orderInfoGoods = null;
					for (int i = 0; i < goodsIdList.size(); i++) {
						orderInfoGoods = new TOrderInfoGoods();
						orderInfoGoods.setOrderGoodsId(Integer.parseInt(orderGoodsIdList.get(i).toString()));
						orderInfoGoods.setOrderId(orderId);
						orderInfoGoods.setShopId((Integer)shopIdList.get(i));
						orderInfoGoods.setShopGoodsId((Integer)goodsIdList.get(i));
						orderInfoGoods.setShopGoodsCount(countList.get(i).toString());
						orderInfoGoodsList.add(orderInfoGoods);
					}
					
					//shopIdList去重
					HashSet h = new HashSet(shopIdList);
					shopIdList.clear();
					shopIdList.addAll(h);
					
					List<TOrderInfoShop> orderShopInfoList = new ArrayList();
					TOrderInfoShop orderInfoShop =null;
					
					
					
					for (int i = 0; i < shopIdList.size(); i++) {
						orderInfoShop =  new TOrderInfoShop();
						int tempShopId = (Integer)shopIdList.get(i);
						orderInfoShop.setShopId(tempShopId);
						orderInfoShop.setOrderId(orderId);
						
						//1:继续订购；2：下单
						if(StringUtils.isNotBlank(type)&& (StringUtils.equals(type, "2")||StringUtils.equals(type, "我要下单"))){
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
							
					        String encoderContent = systemPath+"order/get-order?tel="+sessionUserInfo.getUserInfo().getUserPhoneNumber()+"&orderId="+orderId+"&shopId="+tempShopId;  //order/lc?tel=**shopId=**
					        TwoDimensionCode handler = new TwoDimensionCode();  
					        handler.encoderQRCode(encoderContent, imgPath, "png");
		//				    qcCodePreviewPath=imgPath;
		//			        String separator = System.getProperties().getProperty("file.separator");
		//					qcCodePreviewPath = imgPath.replace(existPath, previewPath).replace("\\", "/");
							
							//=========================生成二维码====================================//
							
							orderInfoShop.setOrderShopQrcode(imgPath);
						}
						
						orderShopInfoList.add(orderInfoShop);
					}
					
					//下单
					result = orderMgr.addOrder(orderInfo, orderShopInfoList, orderInfoGoodsList, sessionUserInfo.getUserInfo(),type);
					
					//1:继续订购；2：下单
					if(StringUtils.isNotBlank(type)&& (StringUtils.equals(type, "1")||StringUtils.equals(type, "继续订购"))){
						return "shopCategoryGoodsList";
					}
					//==========================页面展示查询===========================//
					orderInfo = new TOrderInfo();
					orderInfo.setOrderId(orderId);
					gLogger.info(">>>>> orderId <<<<<"+orderId);
					orderResponseBean = orderMgr.selectOrders(orderInfo);
					list = orderResponseBean.getResultListMap();//存放订单店铺及店铺对应的商品
					orderInfo = orderResponseBean.getOrderInfo();//存放订单总价格和数量
					
					
					Map map = list.get(0);
	//				TOrderInfoShop shop = (TOrderInfoShop)map.get("shopInfo");
					@SuppressWarnings("unchecked")
					List<MyOrder> orderList = (List<MyOrder>)map.get("myOrderList");
					MyOrder order = orderList.get(0);
					if(null == shopId){
						shopId = order.getShopId();
					}
					gLogger.info(">>>>> shopId ====="+shopId);
					order.setShopId(shopId);
					orderResponseBean =orderMgr.selectOrderGoodsByOrderIdAndShopId(order);
					list2 = orderResponseBean.getResultList();//存放订单店铺及店铺对应的商品
					int size = list2.size()-1;
					gLogger.info(">>>>> size ====="+size);
					MyOrder myOrder = (MyOrder)list2.get(size);
					String qrcodepath = myOrder.getOrderShopQrcode();
					gLogger.info("qrcodepath="+qrcodepath);
					System.out.println("path="+CacheConstants.TOP_FILE_PATH());
					if(StringUtils.isNotBlank(qrcodepath)){
						if((qrcodepath.indexOf(CacheConstants.TOP_FILE_PATH()) > -1)){
							gLogger.info("qrcodeAccessPath==qrcodeAccessPath");
							String qrcodeAccessPath = qrcodepath.replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
							gLogger.info("qrcodeAccessPath="+qrcodeAccessPath);
							myOrder.setOrderShopQrcode(qrcodeAccessPath);
						}
						list2.set(size, myOrder);
					}
					
					//==========================页面展示查询===========================//
					if(!result){
						resultFlag = "addOrderError";
					}else{
						resultFlag = "success";
					}
					
				}else{
					//==========================页面展示查询===========================//
					orderInfo = new TOrderInfo();
					orderInfo.setOrderId(orderId);
					gLogger.info(">>>>> orderId <<<<<"+orderId);
					orderResponseBean = orderMgr.selectOrders(orderInfo);
					list = orderResponseBean.getResultListMap();//存放订单店铺及店铺对应的商品
					orderInfo = orderResponseBean.getOrderInfo();//存放订单总价格和数量
					
					if(CollectionUtils.isNotEmpty(list)){
						Map map = list.get(0);
	//					TOrderInfoShop shop = (TOrderInfoShop)map.get("shopInfo");
						@SuppressWarnings("unchecked")
						List<MyOrder> orderList = (List<MyOrder>)map.get("myOrderList");
						MyOrder order = orderList.get(0);
						if(null == shopId){
							shopId = order.getShopId();
						}
						gLogger.info(">>>>> shopId ====="+shopId);
						order.setShopId(shopId);
						orderResponseBean =orderMgr.selectOrderGoodsByOrderIdAndShopId(order);
						list2 = orderResponseBean.getResultList();//存放订单店铺及店铺对应的商品
						int size = list2.size()-1;
						gLogger.info(">>>>> size ====="+size);
						MyOrder myOrder = (MyOrder)list2.get(size);
						String qrcodepath = myOrder.getOrderShopQrcode();
						if(StringUtils.isNotBlank(qrcodepath)){
							if((qrcodepath.indexOf(CacheConstants.TOP_FILE_PATH()) > -1)){
								String qrcodeAccessPath = qrcodepath.replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
								gLogger.info("qrcodeAccessPath="+qrcodeAccessPath);
								myOrder.setOrderShopQrcode(qrcodeAccessPath);
							}
							list2.set(size, myOrder);
						}
						resultFlag = "success";
					}else{
						resultFlag = "shopList";
					}
				}
			}else{
				this.errorMessage =CacheConstants.getParamValueByName("over_time_message");
				resultFlag = "addOrderError";
				result = false;
			}
		}catch(CMRollBackException cmr){
			gLogger.exception(xFunctionName, cmr);
			this.errorMessage =  cmr.getMessage();
			resultFlag = "addOrderError";
			result = false;
		}catch(CMException cme){
			gLogger.exception(xFunctionName, cme);
			int cmeCode = cme.getCode();
			if(cmeCode == 100000){
				this.errorMessage =  cme.getMessage();
			}
			resultFlag = "addOrderError";
			result = false;
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			resultFlag = "addOrderError";
			result = false;
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		
		
		return resultFlag;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public String getShopIdGoodsIdCount() {
		return shopIdGoodsIdCount;
	}

	public void setShopIdGoodsIdCount(String shopIdGoodsIdCount) {
		this.shopIdGoodsIdCount = shopIdGoodsIdCount;
	}

	public String getShopGoodsCount() {
		return shopGoodsCount;
	}

	public void setShopGoodsCount(String shopGoodsCount) {
		this.shopGoodsCount = shopGoodsCount;
	}

	public TOrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(TOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public OrderMgr getOrderMgr() {
		return orderMgr;
	}

	public void setOrderMgr(OrderMgr orderMgr) {
		this.orderMgr = orderMgr;
	}

	public String getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(String orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getQcCodePreviewPath() {
		return qcCodePreviewPath;
	}

	public void setQcCodePreviewPath(String qcCodePreviewPath) {
		this.qcCodePreviewPath = qcCodePreviewPath;
	}

	public List<Map> getList2() {
		return list2;
	}

	public void setList2(List<Map> list2) {
		this.list2 = list2;
	}

	public TOrderInfo getOrderInfo2() {
		return orderInfo2;
	}

	public void setOrderInfo2(TOrderInfo orderInfo2) {
		this.orderInfo2 = orderInfo2;
	}

	public GoodsRequestBean getGoodsRequestBean() {
		return goodsRequestBean;
	}

	public void setGoodsRequestBean(GoodsRequestBean goodsRequestBean) {
		this.goodsRequestBean = goodsRequestBean;
	}

	public GoodsResponseBean getGoodsResponseBean() {
		return goodsResponseBean;
	}

	public void setGoodsResponseBean(GoodsResponseBean goodsResponseBean) {
		this.goodsResponseBean = goodsResponseBean;
	}

	public TSysFile getSysFile() {
		return sysFile;
	}

	public void setSysFile(TSysFile sysFile) {
		this.sysFile = sysFile;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public String getTo() {
	
		return to;
	}

	public void setTo(String to) {
	
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	

}
