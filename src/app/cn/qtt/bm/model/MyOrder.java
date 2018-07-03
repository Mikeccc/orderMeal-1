/**
 * 
 */
package app.cn.qtt.bm.model;

/**
 * @author Gabriel Ge
 * @Description 我的饭盒列表
 * @date 2013-6-25 上午11:44:09
 * @type MyOrder
 * @project BespeakMeal
 */
public class MyOrder {
	
	private Integer orderGoodsId;
	private Integer orderId;
	private Integer shopId;
	private String shopName;
	private Integer shopGoodsId;
	private String shopGoodsName;
	private Integer shopGoodsPrice;
	private Integer shopGoodsCount;
	private String orderShopQrcode;
	private String userCode;
	private String orderShopRunStatus;
	private String createTime;
	private String endTime;
	private String captchas;
	private String fileAccessUrl;
	
	public String getFileAccessUrl() {
		return fileAccessUrl;
	}
	public void setFileAccessUrl(String fileAccessUrl) {
		this.fileAccessUrl = fileAccessUrl;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrderShopRunStatus() {
		return orderShopRunStatus;
	}
	public void setOrderShopRunStatus(String orderShopRunStatus) {
		this.orderShopRunStatus = orderShopRunStatus;
	}
	public String getOrderShopQrcode() {
		return orderShopQrcode;
	}
	public void setOrderShopQrcode(String orderShopQrcode) {
		this.orderShopQrcode = orderShopQrcode;
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
	 * @return the shopGoodsId
	 */
	public Integer getShopGoodsId() {
		return shopGoodsId;
	}
	/**
	 * @param shopGoodsId the shopGoodsId to set
	 */
	public void setShopGoodsId(Integer shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getOrderGoodsId() {
		return orderGoodsId;
	}
	public void setOrderGoodsId(Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * @return 返回 captchas
	 */
	public String getCaptchas() {
		return captchas;
	}
	/**
	 * @param 对captchas进行赋值
	 */
	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}
	
}
