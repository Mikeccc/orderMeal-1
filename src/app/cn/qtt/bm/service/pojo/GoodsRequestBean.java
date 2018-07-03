/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsExample;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopCategoryGoodsLinkExample;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsExample;
import app.cn.qtt.bm.model.TShopGoodsTime;
import app.cn.qtt.bm.model.TShopGoodsTimeExample;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysFile;

/**
 * @author GeYanmeng
 * @Description 商品信息请求对象
 * @date 2013-6-9 下午3:33:05
 * @type GoodsRequestBean
 * @project BespeakMeal
 */
public class GoodsRequestBean extends RequestBean {
	//商店信息
	TShopInfo shopInfo;
	//商品信息
	TShopGoods shopGoods;
	TShopGoodsExample shopGoodsExample;
	//商品分类
	TShopCategoryGoods shopCategoryGoods;
	TShopCategoryGoodsExample shopCategoryGoodsExample;
	//商品分类和商品关联
	TShopCategoryGoodsLink shopCategoryGoodsLink;
	TShopCategoryGoodsLinkExample shopCategoryGoodsLinkExample;
	//商品供应时间关联
	TShopGoodsTime shopGoodsTime;
	//上传的图片文件
	TSysFile sysFile;
	TShopGoodsTimeExample shopGoodsTimeExample;
	//订单ID
	Integer orderId;
	//供应时间
	List<String> timeCodeList;
	
	/**
	 * @return the shopGoods
	 */
	public TShopGoods getShopGoods() {
		return shopGoods;
	}
	/**
	 * @param shopGoods the shopGoods to set
	 */
	public void setShopGoods(TShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}
	/**
	 * @return the shopGoodsExample
	 */
	public TShopGoodsExample getShopGoodsExample() {
		return shopGoodsExample;
	}
	/**
	 * @param shopGoodsExample the shopGoodsExample to set
	 */
	public void setShopGoodsExample(TShopGoodsExample shopGoodsExample) {
		this.shopGoodsExample = shopGoodsExample;
	}
	/**
	 * @return the shopCategoryGoods
	 */
	public TShopCategoryGoods getShopCategoryGoods() {
		return shopCategoryGoods;
	}
	/**
	 * @param shopCategoryGoods the shopCategoryGoods to set
	 */
	public void setShopCategoryGoods(TShopCategoryGoods shopCategoryGoods) {
		this.shopCategoryGoods = shopCategoryGoods;
	}
	/**
	 * @return the shopCategoryGoodsExample
	 */
	public TShopCategoryGoodsExample getShopCategoryGoodsExample() {
		return shopCategoryGoodsExample;
	}
	/**
	 * @param shopCategoryGoodsExample the shopCategoryGoodsExample to set
	 */
	public void setShopCategoryGoodsExample(
			TShopCategoryGoodsExample shopCategoryGoodsExample) {
		this.shopCategoryGoodsExample = shopCategoryGoodsExample;
	}
	/**
	 * @return the shopCategoryGoodsLink
	 */
	public TShopCategoryGoodsLink getShopCategoryGoodsLink() {
		return shopCategoryGoodsLink;
	}
	/**
	 * @param shopCategoryGoodsLink the shopCategoryGoodsLink to set
	 */
	public void setShopCategoryGoodsLink(
			TShopCategoryGoodsLink shopCategoryGoodsLink) {
		this.shopCategoryGoodsLink = shopCategoryGoodsLink;
	}
	/**
	 * @return the shopCategoryGoodsLinkExample
	 */
	public TShopCategoryGoodsLinkExample getShopCategoryGoodsLinkExample() {
		return shopCategoryGoodsLinkExample;
	}
	/**
	 * @param shopCategoryGoodsLinkExample the shopCategoryGoodsLinkExample to set
	 */
	public void setShopCategoryGoodsLinkExample(
			TShopCategoryGoodsLinkExample shopCategoryGoodsLinkExample) {
		this.shopCategoryGoodsLinkExample = shopCategoryGoodsLinkExample;
	}
	/**
	 * @return the shopGoodsTime
	 */
	public TShopGoodsTime getShopGoodsTime() {
		return shopGoodsTime;
	}
	/**
	 * @param shopGoodsTime the shopGoodsTime to set
	 */
	public void setShopGoodsTime(TShopGoodsTime shopGoodsTime) {
		this.shopGoodsTime = shopGoodsTime;
	}
	/**
	 * @return the shopGoodsTimeExample
	 */
	public TShopGoodsTimeExample getShopGoodsTimeExample() {
		return shopGoodsTimeExample;
	}
	/**
	 * @param shopGoodsTimeExample the shopGoodsTimeExample to set
	 */
	public void setShopGoodsTimeExample(TShopGoodsTimeExample shopGoodsTimeExample) {
		this.shopGoodsTimeExample = shopGoodsTimeExample;
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
	 * @return the timeCodeList
	 */
	public List<String> getTimeCodeList() {
		return timeCodeList;
	}
	/**
	 * @param timeCodeList the timeCodeList to set
	 */
	public void setTimeCodeList(List<String> timeCodeList) {
		this.timeCodeList = timeCodeList;
	}
	public TSysFile getSysFile() {
		return sysFile;
	}
	public void setSysFile(TSysFile sysFile) {
		this.sysFile = sysFile;
	}
	
}
