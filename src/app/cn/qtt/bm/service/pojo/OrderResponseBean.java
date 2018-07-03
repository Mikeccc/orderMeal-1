/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoShop;

/**
 * @author GeYanmeng
 * @Description 订单信息响应类
 * @date 2013-6-9 下午4:16:34
 * @type OrderResponseBean
 * @project BespeakMeal
 */
public class OrderResponseBean extends ResponseBean {
	
	/**
	 * 订单商店信息
	 */
	private TOrderInfoShop orderInfoShop;
	/**
	 * 订单商品信息
	 */
	private TOrderInfoGoods orderInfoGoods;
	
	private List<Map> resultListMap;
	/**
	 * 订单单位商店下的总价格
	 */
	private String price;
	
	TOrderInfo orderInfo;
	private int updateReturnValue;
	
	/**
	 * 订单物品总价格
	 */
	private String orderGoodsPrice;
	
	private String orderGoodsCount;
	
	
	private OrderOperateBean orderOperateBean;
	
	/**
	 * 数据查询返回值
	 */
	private int dbReturnValue = 0;
	
	private String tel;
	
	/**
	 * @return the orderGoodsPrice
	 */
	public String getOrderGoodsPrice() {
		return orderGoodsPrice;
	}
	/**
	 * @param orderGoodsPrice the orderGoodsPrice to set
	 */
	public void setOrderGoodsPrice(String orderGoodsPrice) {
		this.orderGoodsPrice = orderGoodsPrice;
	}
	/**
	 * 每日订单价格统计（按分组）
	 */
	int priceCountDaily;
	/**
	 * 每日订单总量统计（按分组）
	 */
	int numberCountDaily;
	/**
	 * 每日订单未领取数量统计（按分组）
	 */
	int noGetNumCountDaily;
	
	/**
	 * @return the orderInfoShop
	 */
	public TOrderInfoShop getOrderInfoShop() {
		return orderInfoShop;
	}
	/**
	 * @param orderInfoShop the orderInfoShop to set
	 */
	public void setOrderInfoShop(TOrderInfoShop orderInfoShop) {
		this.orderInfoShop = orderInfoShop;
	}
	/**
	 * @return the orderInfoGoods
	 */
	public TOrderInfoGoods getOrderInfoGoods() {
		return orderInfoGoods;
	}
	/**
	 * @param orderInfoGoods the orderInfoGoods to set
	 */
	public void setOrderInfoGoods(TOrderInfoGoods orderInfoGoods) {
		this.orderInfoGoods = orderInfoGoods;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	public TOrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(TOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<Map> getResultListMap() {
		return resultListMap;
	}
	public void setResultListMap(List<Map> resultListMap) {
		this.resultListMap = resultListMap;
	}
	public int getPriceCountDaily() {
		return priceCountDaily;
	}
	public void setPriceCountDaily(int priceCountDaily) {
		this.priceCountDaily = priceCountDaily;
	}
	public int getNumberCountDaily() {
		return numberCountDaily;
	}
	public void setNumberCountDaily(int numberCountDaily) {
		this.numberCountDaily = numberCountDaily;
	}
	public int getNoGetNumCountDaily() {
		return noGetNumCountDaily;
	}
	public void setNoGetNumCountDaily(int noGetNumCountDaily) {
		this.noGetNumCountDaily = noGetNumCountDaily;
	}
	/**
	 * @return the orderGoodsCount
	 */
	public String getOrderGoodsCount() {
		return orderGoodsCount;
	}
	/**
	 * @param orderGoodsCount the orderGoodsCount to set
	 */
	public void setOrderGoodsCount(String orderGoodsCount) {
		this.orderGoodsCount = orderGoodsCount;
	}
	/**
	 * @return the updateReturnValue
	 */
	public int getUpdateReturnValue() {
		return updateReturnValue;
	}
	/**
	 * @param updateReturnValue the updateReturnValue to set
	 */
	public void setUpdateReturnValue(int updateReturnValue) {
		this.updateReturnValue = updateReturnValue;
	}
	public OrderOperateBean getOrderOperateBean() {
		return orderOperateBean;
	}
	public void setOrderOperateBean(OrderOperateBean orderOperateBean) {
		this.orderOperateBean = orderOperateBean;
	}
	/**
	 * @return 返回 dbReturnValue
	 */
	public int getDbReturnValue() {
		return dbReturnValue;
	}
	/**
	 * @param 对dbReturnValue进行赋值
	 */
	public void setDbReturnValue(int dbReturnValue) {
		this.dbReturnValue = dbReturnValue;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
