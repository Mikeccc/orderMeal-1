package app.cn.qtt.bm.dao;

import java.util.List;

import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TShopInfoExample;

public interface TShopInfoDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TShopInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKey(TShopInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TShopInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TShopInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TShopInfo selectByPrimaryKey(Integer shopId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TShopInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(Integer shopId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TShopInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TShopInfo record, TShopInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TShopInfo record, TShopInfoExample example);

	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageRecords
	 * @param example
	 * @return List<TShopInfo>
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public List<TShopInfo> selectPagesByExample(int currentPage, int pageRecords,
			TShopInfoExample example);
	
}