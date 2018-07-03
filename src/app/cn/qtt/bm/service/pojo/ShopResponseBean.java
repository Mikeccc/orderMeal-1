/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.service.pojo.ResponseBean;

/**
 * @author GeYanmeng
 * @Description 店铺相关响应类
 * @date 2013-6-9 下午2:54:06
 * @type ShopResponseBean
 * @project BespeakMeal
 */
public class ShopResponseBean extends ResponseBean {
	TShopInfo shopInfo;

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
	
}
