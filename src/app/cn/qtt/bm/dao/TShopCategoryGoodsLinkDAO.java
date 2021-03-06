package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopCategoryGoodsLinkExample;
import java.util.List;

public interface TShopCategoryGoodsLinkDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	void insert(TShopCategoryGoodsLink record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int updateByPrimaryKey(TShopCategoryGoodsLink record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int updateByPrimaryKeySelective(TShopCategoryGoodsLink record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	List selectByExample(TShopCategoryGoodsLinkExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	TShopCategoryGoodsLink selectByPrimaryKey(Integer categoryGoodsId);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int deleteByExample(TShopCategoryGoodsLinkExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int deleteByPrimaryKey(Integer categoryGoodsId);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int countByExample(TShopCategoryGoodsLinkExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int updateByExampleSelective(TShopCategoryGoodsLink record,
			TShopCategoryGoodsLinkExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	int updateByExample(TShopCategoryGoodsLink record,
			TShopCategoryGoodsLinkExample example);
}