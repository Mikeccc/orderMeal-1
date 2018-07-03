package app.cn.qtt.bm.dao;

import java.util.List;

import app.cn.qtt.bm.model.NewTShopGoods;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsExample;

public interface TShopGoodsDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int insert(TShopGoods record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKey(TShopGoods record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TShopGoods record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TShopGoodsExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TShopGoods selectByPrimaryKey(Integer shopGoodsId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TShopGoodsExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(Integer shopGoodsId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TShopGoodsExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TShopGoods record, TShopGoodsExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TShopGoods record, TShopGoodsExample example);
    
    /**
     * 分页查询
     * @param currentPage
     * @param pageRecords
     * @param categoryId
     * @param timeCodeList
     * @param shopId
     * @param example
     * @return List<TShopGoods>
     * @author GeYanmeng
     * @date 2013-6-13
     */
    public List<NewTShopGoods> selectPagesByExample(int currentPage,
			int pageRecords,Integer categoryId,List<String> timeCodeList,Integer shopId,TShopGoodsExample example);

	/**
	 * 查找分页总数
	 * @param example
	 * @return 
	 * @author Gabriel
	 * @date 2013-7-2
	 */
	int countTotalRecordsByExample(Integer categoryId,List<String> timeCodeList,Integer shopId,TShopGoodsExample example);
    
	 List selectImageByExample(TShopGoodsExample example);
}