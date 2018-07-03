package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TShopGoodsDeal;
import app.cn.qtt.bm.model.TShopGoodsDealExample;
import java.util.List;

public interface TShopGoodsDealDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TShopGoodsDeal record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKey(TShopGoodsDeal record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TShopGoodsDeal record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TShopGoodsDealExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TShopGoodsDeal selectByPrimaryKey(Integer shopGoodsDealId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TShopGoodsDealExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(Integer shopGoodsDealId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TShopGoodsDealExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TShopGoodsDeal record, TShopGoodsDealExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TShopGoodsDeal record, TShopGoodsDealExample example);
}