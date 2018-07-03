/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.ShopOrderdailyStatistics;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoGoodsExample;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;

/**
 * @author GeYanmeng
 * @Description 订单信息请求类
 * @date 2013-6-9 下午4:16:50
 * @type OrderRequestBean
 * @project BespeakMeal
 */
public class OrderRequestBean extends RequestBean {
	//订单信息
	TOrderInfo orderInfo;
	TOrderInfoExample orderInfoExample;
	//订单,商品关联信息
	TOrderInfoGoods orderInfoGoods;
	TOrderInfoGoodsExample orderInfoGoodsExample;
	//订单,商店关联信息
	TOrderInfoShop orderInfoShop;
	TOrderInfoShopExample orderInfoShopExample;
	//订单每日统计信息(发送给商家的)
	TShopDailyInfo shopDailyInfo;
	TShopDailyInfoExample shopDailyInfoExample;
	//商店订单详情每日统计
	ShopOrderdailyStatistics shopOrderdailyStatistics;
	//我的饭盒信息
	MyOrder myOrder;
	//发给商家订单统计信息列表
	List<TShopDailyInfo> shopDailyInfoList;
	
	//查询日期
	private String searchDate;
	/**
	 * 订单shop状态 Added by Gabriel 2013-11-13
	 */
	private String orderShopRunStatus;
	/**
	 * 订单主状态
	 */
	private String orderRunStatus;

	/**
	 * 手机号码后4位
	 * Added by Gabriel 2013-09-17
	 */
	private String shortPhoneNumber;
	/**
	 * 验证码
	 * Added by Gabriel 2013-09-17
	 */
	private String captchas;
	
	/**
	 * @return the orderInfo
	 */
	public TOrderInfo getOrderInfo() {
		return orderInfo;
	}

	/**
	 * @param orderInfo
	 *            the orderInfo to set
	 */
	public void setOrderInfo(TOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	/**
	 * @return the orderInfoExample
	 */
	public TOrderInfoExample getOrderInfoExample() {
		return orderInfoExample;
	}

	/**
	 * @param orderInfoExample
	 *            the orderInfoExample to set
	 */
	public void setOrderInfoExample(TOrderInfoExample orderInfoExample) {
		this.orderInfoExample = orderInfoExample;
	}

	/**
	 * @return the orderInfoGoods
	 */
	public TOrderInfoGoods getOrderInfoGoods() {
		return orderInfoGoods;
	}

	/**
	 * @param orderInfoGoods
	 *            the orderInfoGoods to set
	 */
	public void setOrderInfoGoods(TOrderInfoGoods orderInfoGoods) {
		this.orderInfoGoods = orderInfoGoods;
	}

	/**
	 * @return the orderInfoGoodsExample
	 */
	public TOrderInfoGoodsExample getOrderInfoGoodsExample() {
		return orderInfoGoodsExample;
	}

	/**
	 * @param orderInfoGoodsExample
	 *            the orderInfoGoodsExample to set
	 */
	public void setOrderInfoGoodsExample(
			TOrderInfoGoodsExample orderInfoGoodsExample) {
		this.orderInfoGoodsExample = orderInfoGoodsExample;
	}

	/**
	 * @return the orderInfoShop
	 */
	public TOrderInfoShop getOrderInfoShop() {
		return orderInfoShop;
	}

	/**
	 * @param orderInfoShop
	 *            the orderInfoShop to set
	 */
	public void setOrderInfoShop(TOrderInfoShop orderInfoShop) {
		this.orderInfoShop = orderInfoShop;
	}

	/**
	 * @return the orderInfoShopExample
	 */
	public TOrderInfoShopExample getOrderInfoShopExample() {
		return orderInfoShopExample;
	}

	/**
	 * @param orderInfoShopExample
	 *            the orderInfoShopExample to set
	 */
	public void setOrderInfoShopExample(
			TOrderInfoShopExample orderInfoShopExample) {
		this.orderInfoShopExample = orderInfoShopExample;
	}

	/**
	 * @return the shopDailyInfo
	 */
	public TShopDailyInfo getShopDailyInfo() {
		return shopDailyInfo;
	}

	/**
	 * @param shopDailyInfo the shopDailyInfo to set
	 */
	public void setShopDailyInfo(TShopDailyInfo shopDailyInfo) {
		this.shopDailyInfo = shopDailyInfo;
	}

	/**
	 * @return the shopDailyInfoExample
	 */
	public TShopDailyInfoExample getShopDailyInfoExample() {
		return shopDailyInfoExample;
	}

	/**
	 * @param shopDailyInfoExample the shopDailyInfoExample to set
	 */
	public void setShopDailyInfoExample(TShopDailyInfoExample shopDailyInfoExample) {
		this.shopDailyInfoExample = shopDailyInfoExample;
	}

	/**
	 * @return the shopOrderdailyStatistics
	 */
	public ShopOrderdailyStatistics getShopOrderdailyStatistics() {
		return shopOrderdailyStatistics;
	}

	/**
	 * @param shopOrderdailyStatistics the shopOrderdailyStatistics to set
	 */
	public void setShopOrderdailyStatistics(
			ShopOrderdailyStatistics shopOrderdailyStatistics) {
		this.shopOrderdailyStatistics = shopOrderdailyStatistics;
	}

	/**
	 * @return the myOrder
	 */
	public MyOrder getMyOrder() {
		return myOrder;
	}

	/**
	 * @param myOrder the myOrder to set
	 */
	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}

	/**
	 * @return the shopDailyInfoList
	 */
	public List<TShopDailyInfo> getShopDailyInfoList() {
		return shopDailyInfoList;
	}

	/**
	 * @param shopDailyInfoList the shopDailyInfoList to set
	 */
	public void setShopDailyInfoList(List<TShopDailyInfo> shopDailyInfoList) {
		this.shopDailyInfoList = shopDailyInfoList;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return 返回 shortPhoneNumber
	 */
	public String getShortPhoneNumber() {
		return shortPhoneNumber;
	}

	/**
	 * @return 返回 captchas
	 */
	public String getCaptchas() {
		return captchas;
	}

	/**
	 * @param 对shortPhoneNumber进行赋值
	 */
	public void setShortPhoneNumber(String shortPhoneNumber) {
		this.shortPhoneNumber = shortPhoneNumber;
	}

	/**
	 * @param 对captchas进行赋值
	 */
	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}

	/**
	 * @return 返回 orderShopRunStatus
	 */
	public String getOrderShopRunStatus() {
		return orderShopRunStatus;
	}

	/**
	 * @param 对orderShopRunStatus进行赋值
	 */
	public void setOrderShopRunStatus(String orderShopRunStatus) {
		this.orderShopRunStatus = orderShopRunStatus;
	}

	/**
	 * @return 返回 orderRunStatus
	 */
	public String getOrderRunStatus() {
		return orderRunStatus;
	}

	/**
	 * @param 对orderRunStatus进行赋值
	 */
	public void setOrderRunStatus(String orderRunStatus) {
		this.orderRunStatus = orderRunStatus;
	}

}
