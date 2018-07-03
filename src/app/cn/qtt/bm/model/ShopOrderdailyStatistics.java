/**
 * 
 */
package app.cn.qtt.bm.model;


/**
 * @author GeYanmeng
 * @Description
 * @date 2013-6-17 下午5:47:37
 * @type ShopOrderdailyStatistics
 * @project BespeakMeal
 */
public class ShopOrderdailyStatistics {
	
	private Integer categoryId;
	private Integer shopId;
    private String shopGoodsName;
    private Integer shopGoodsPrice;
    private Integer shopGoodsCount;
    /**
     * 未领取的数量
     */
    private Integer orderRunStatusCount;
    
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the shopId
	 */
	public Integer getShopId() {
		return shopId;
	}
	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
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
	 * @return the shopGoodsPrice
	 */
	public Integer getShopGoodsPrice() {
		return shopGoodsPrice;
	}
	/**
	 * @param shopGoodsPrice the shopGoodsPrice to set
	 */
	public void setShopGoodsPrice(Integer shopGoodsPrice) {
		this.shopGoodsPrice = shopGoodsPrice;
	}
	/**
	 * @return the shopGoodsCount
	 */
	public Integer getShopGoodsCount() {
		return shopGoodsCount;
	}
	/**
	 * @param shopGoodsCount the shopGoodsCount to set
	 */
	public void setShopGoodsCount(Integer shopGoodsCount) {
		this.shopGoodsCount = shopGoodsCount;
	}
	/**
	 * @return the orderRunStatusCount
	 */
	public Integer getOrderRunStatusCount() {
		return orderRunStatusCount;
	}
	/**
	 * @param orderRunStatusCount the orderRunStatusCount to set
	 */
	public void setOrderRunStatusCount(Integer orderRunStatusCount) {
		this.orderRunStatusCount = orderRunStatusCount;
	}
    
}
