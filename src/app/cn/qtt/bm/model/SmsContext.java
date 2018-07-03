/**
 * 
 */
package app.cn.qtt.bm.model;

/**
 * @author Gabriel
 * @Description 发给商家的信息
 * @date 2013-6-26 下午9:06:44
 * @type SmsContext
 * @project BespeakMeal
 */
public class SmsContext {
	private String shopName;
	private String shopGoodsName;
	private String shopGoodsCount;
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * @return the shopGoodsName
	 */
	public String getShopGoodsName() {
		return shopGoodsName;
	}
	/**
	 * @param shopGoodsName the shopGoodsName to set
	 */
	public void setShopGoodsName(String shopGoodsName) {
		this.shopGoodsName = shopGoodsName;
	}
	/**
	 * @return the shopGoodsCount
	 */
	public String getShopGoodsCount() {
		return shopGoodsCount;
	}
	/**
	 * @param shopGoodsCount the shopGoodsCount to set
	 */
	public void setShopGoodsCount(String shopGoodsCount) {
		this.shopGoodsCount = shopGoodsCount;
	}
	
}
