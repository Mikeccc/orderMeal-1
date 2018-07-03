/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import app.cn.qtt.bm.model.NewTShopGoods;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopGoods;

/**
 * @author GeYanmeng
 * @Description 商品信息响应对象
 * @date 2013-6-9 下午3:55:20
 * @type GoodsResponseBean
 * @project BespeakMeal
 */
public class GoodsResponseBean extends ResponseBean{
	/**
	 * 商品信息
	 */
	private TShopGoods shopGoods;
	private NewTShopGoods newShopGoods;
	private int shopGoodsId;
	private int fileId;
	private int categoryId;
	private int categoryCountNum;
	/**
	 * 商品分组信息
	 */
	private TShopCategoryGoods shopCategoryGoods;
	/**
	 * 商品分组关联信息
	 */
	private TShopCategoryGoodsLink shopCategoryGoodsLink;
	
	
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
	 * @return the shopGoodsId
	 */
	public int getShopGoodsId() {
		return shopGoodsId;
	}

	/**
	 * @param shopGoodsId the shopGoodsId to set
	 */
	public void setShopGoodsId(int shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
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

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryCountNum() {
		return categoryCountNum;
	}

	public void setCategoryCountNum(int categoryCountNum) {
		this.categoryCountNum = categoryCountNum;
	}

	public NewTShopGoods getNewShopGoods() {
		return newShopGoods;
	}

	public void setNewShopGoods(NewTShopGoods newShopGoods) {
		this.newShopGoods = newShopGoods;
	}
	
}
