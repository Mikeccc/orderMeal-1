/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TShopInfoExample;
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.service.pojo.RequestBean;

/**
 * @author GeYanmeng
 * @Description 店铺请求类
 * @date 2013-6-9 下午2:56:22
 * @type ShopRequestBean
 * @project BespeakMeal
 */
public class ShopRequestBean extends RequestBean {
	/**
	 * 店铺信息对象
	 */
	TShopInfo shopInfo;
	
	TSysFile sysFile;
	public TSysFile getSysFile() {
		return sysFile;
	}

	public void setSysFile(TSysFile sysFile) {
		this.sysFile = sysFile;
	}

	/**
	 * 店铺数据库操作对象
	 */
	TShopInfoExample shopInfoExample;
	
	private Integer orderId;

	/**
	 * @return the shopInfo
	 */
	public TShopInfo getShopInfo() {
		return shopInfo;
	}

	/**
	 * @param shopInfo the shopInfo to set
	 */
	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

	/**
	 * @return the shopInfoExample
	 */
	public TShopInfoExample getShopInfoExample() {
		return shopInfoExample;
	}

	/**
	 * @param shopInfoExample the shopInfoExample to set
	 */
	public void setShopInfoExample(TShopInfoExample shopInfoExample) {
		this.shopInfoExample = shopInfoExample;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
